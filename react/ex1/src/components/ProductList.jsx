import ProductItem from "./ProductItem";

function ProductList() {
  const products = [
    { id: 1, name: "노트북", price: 1000000 },
    { id: 2, name: "마우스", price: 20000 },
  ];

  return (
    <div>
      <div className='block'>
      <h2>4번 — 상품 리스트</h2>

      {products.map((product) => (
        <ProductItem key={product.id} product={product} />
      ))}
      </div>
    </div>
  );
}

export default ProductList;