from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
from sqlalchemy import create_engine, Column, Integer, String, Double, Boolean, ForeignKey, DateTime
from sqlalchemy.orm import sessionmaker, declarative_base, relationship
from datetime import datetime

# ===============================
# CONFIGURACIÓN BD
# ===============================

DATABASE_URL = "mysql+pymysql://root:@localhost/Tienda"

engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(bind=engine)
Base = declarative_base()

# ===============================
# MODELOS
# ===============================

class Usuario(Base):
    __tablename__ = "usuarios"

    id = Column(Integer, primary_key=True, index=True)
    user = Column(String(80), unique=True)
    password = Column(String(255))
    activo = Column(Boolean, default=True)
    role = Column(String(20), default="CLIENTE")


class Producto(Base):
    __tablename__ = "productos"

    id = Column(Integer, primary_key=True, index=True)
    nombre = Column(String(120))
    marca = Column(String(120))
    precio = Column(Double)
    stock = Column(Integer)
    activo = Column(Boolean, default=True)


class Venta(Base):
    __tablename__ = "ventas"

    id = Column(Integer, primary_key=True, index=True)
    usuario_id = Column(Integer, ForeignKey("usuarios.id"))
    total = Column(Double)
    fecha = Column(DateTime, default=datetime.utcnow)
    estado = Column(String(30), default="PAGADO")

    detalles = relationship("VentaDetalle", back_populates="venta")


class VentaDetalle(Base):
    __tablename__ = "venta_detalle"

    id = Column(Integer, primary_key=True, index=True)
    venta_id = Column(Integer, ForeignKey("ventas.id"))
    producto_id = Column(Integer, ForeignKey("productos.id"))
    cantidad = Column(Integer)
    precio_unitario = Column(Double)

    venta = relationship("Venta", back_populates="detalles")


Base.metadata.create_all(bind=engine)

# ===============================
# APP
# ===============================

app = FastAPI()

# CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# ===============================
# ESQUEMAS
# ===============================

class ProductoSchema(BaseModel):
    nombre: str
    marca: str
    precio: float
    stock: int
    activo: bool = True


class VentaSchema(BaseModel):
    usuario_id: int
    productos: list


# ===============================
# ENDPOINTS
# ===============================

# 🔹 Crear producto (ADMIN)
@app.post("/productos")
def crear_producto(producto: ProductoSchema):
    db = SessionLocal()

    nuevo = Producto(
        nombre=producto.nombre,
        marca=producto.marca,
        precio=producto.precio,
        stock=producto.stock,
        activo=producto.activo
    )

    db.add(nuevo)
    db.commit()
    db.refresh(nuevo)
    db.close()

    return {"mensaje": "Producto creado", "producto": nuevo.id}


# 🔹 Ver productos (CLIENTE)
@app.get("/productos")
def listar_productos():
    db = SessionLocal()
    productos = db.query(Producto).filter(Producto.activo == True).all()
    db.close()
    return productos


# 🔹 Registrar venta (CLIENTE)
@app.post("/ventas")
def registrar_venta(venta: VentaSchema):
    db = SessionLocal()

    total = 0

    nueva_venta = Venta(usuario_id=venta.usuario_id, total=0)
    db.add(nueva_venta)
    db.commit()
    db.refresh(nueva_venta)

    for item in venta.productos:
        producto = db.query(Producto).filter(Producto.id == item["producto_id"]).first()

        if not producto:
            raise HTTPException(status_code=404, detail="Producto no encontrado")

        if producto.stock < item["cantidad"]:
            raise HTTPException(status_code=400, detail="Stock insuficiente")

        subtotal = producto.precio * item["cantidad"]
        total += subtotal

        producto.stock -= item["cantidad"]

        detalle = VentaDetalle(
            venta_id=nueva_venta.id,
            producto_id=producto.id,
            cantidad=item["cantidad"],
            precio_unitario=producto.precio
        )

        db.add(detalle)

    nueva_venta.total = total
    db.commit()
    db.close()

    return {"mensaje": "Venta registrada", "total": total}


# 🔹 Ver ventas (ADMIN)
@app.get("/ventas")
def ver_ventas():
    db = SessionLocal()
    ventas = db.query(Venta).all()
    db.close()
    return ventas
