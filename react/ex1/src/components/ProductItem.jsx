function ProductItem({ product }) {
  return (
    <div>
      {product.name} - {product.price}원
    </div>
  );
}

export default ProductItem;