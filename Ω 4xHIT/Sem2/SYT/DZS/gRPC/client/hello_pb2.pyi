from google.protobuf.internal import containers as _containers
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Iterable as _Iterable, Mapping as _Mapping, Optional as _Optional, Union as _Union

DESCRIPTOR: _descriptor.FileDescriptor

class WarehouseRequest(_message.Message):
    __slots__ = ("id",)
    ID_FIELD_NUMBER: _ClassVar[int]
    id: str
    def __init__(self, id: _Optional[str] = ...) -> None: ...

class Product(_message.Message):
    __slots__ = ("id", "name", "category", "amount", "unit")
    ID_FIELD_NUMBER: _ClassVar[int]
    NAME_FIELD_NUMBER: _ClassVar[int]
    CATEGORY_FIELD_NUMBER: _ClassVar[int]
    AMOUNT_FIELD_NUMBER: _ClassVar[int]
    UNIT_FIELD_NUMBER: _ClassVar[int]
    id: str
    name: str
    category: str
    amount: str
    unit: str
    def __init__(self, id: _Optional[str] = ..., name: _Optional[str] = ..., category: _Optional[str] = ..., amount: _Optional[str] = ..., unit: _Optional[str] = ...) -> None: ...

class WarehouseData(_message.Message):
    __slots__ = ("id", "name", "timestamp", "street", "city", "country", "plz", "product_data")
    ID_FIELD_NUMBER: _ClassVar[int]
    NAME_FIELD_NUMBER: _ClassVar[int]
    TIMESTAMP_FIELD_NUMBER: _ClassVar[int]
    STREET_FIELD_NUMBER: _ClassVar[int]
    CITY_FIELD_NUMBER: _ClassVar[int]
    COUNTRY_FIELD_NUMBER: _ClassVar[int]
    PLZ_FIELD_NUMBER: _ClassVar[int]
    PRODUCT_DATA_FIELD_NUMBER: _ClassVar[int]
    id: str
    name: str
    timestamp: str
    street: str
    city: str
    country: str
    plz: str
    product_data: _containers.RepeatedCompositeFieldContainer[Product]
    def __init__(self, id: _Optional[str] = ..., name: _Optional[str] = ..., timestamp: _Optional[str] = ..., street: _Optional[str] = ..., city: _Optional[str] = ..., country: _Optional[str] = ..., plz: _Optional[str] = ..., product_data: _Optional[_Iterable[_Union[Product, _Mapping]]] = ...) -> None: ...
