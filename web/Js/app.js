
const cart = {};
let iconCart = document.querySelector('.icon-cart');
let closeCart = document.querySelector('.close');
let body = document.querySelector('body');
let cartContainer = document.getElementById('cart');

iconCart.addEventListener('click', toggleCart);
closeCart.addEventListener('click', toggleCart);

function toggleCart() {
    body.classList.toggle('showCart');
}

function addToCart(event) {
  const button = event.target;
  const card = button.closest('.dishGrid__item, .ourSpecials__item');
  const productId = card.getAttribute('data-product-id');
  const productName = card.getAttribute('data-product-name');
  const productPrice = parseFloat(card.getAttribute('data-product-price'));

  if (!cart[productId]) {
    cart[productId] = { name: productName, price: productPrice, quantity: 1 };
  } else {
    cart[productId].quantity += 1;
  }

  updateCartDisplay();
  openCart();  // Open the cart when a product is added
  
}

function updateCartDisplay() {
  cartContainer.innerHTML = ''; // Clear current cart display

  if (Object.keys(cart).length === 0) {
    cartContainer.innerHTML = `<strong>Your Cart is empty</strong>`;
    return;
  }

  let itemIndex = 1;

  for (const id in cart) {
    const item = cart[id];
    const itemDiv = document.createElement('div');
    itemDiv.className = 'cart-item';
    itemDiv.innerHTML = `
      <span>${itemIndex}. ${item.name}: ₹${item.price} x ${item.quantity} = ₹${item.price * item.quantity}</span>
      <button class="quantity-btn" onclick="increaseQuantity('${id}')">+</button>
      <button class="quantity-btn" onclick="decreaseQuantity('${id}')">-</button>
      <button class="delete-btn" onclick="deleteFromCart('${id}')">Delete</button>
    `;

    cartContainer.appendChild(itemDiv);
    itemIndex++;
  }

  const totalPrice = Object.values(cart).reduce((acc, item) => acc + item.price * item.quantity, 0);
  const totalDiv = document.createElement('div');
  totalDiv.innerHTML = `<strong><br>Total: ₹${totalPrice}</strong>`;
  cartContainer.appendChild(totalDiv);
}

function increaseQuantity(productId) {
  cart[productId].quantity += 1;
  updateCartDisplay();
}

function decreaseQuantity(productId) {
  if (cart[productId].quantity > 1) {
    cart[productId].quantity -= 1;
  } else {
    delete cart[productId];
  }

  updateCartDisplay();
}

function deleteFromCart(productId) {
  delete cart[productId];
  updateCartDisplay();
}

function openCart() {
  // Make sure the cart is shown when an item is added
  if (!body.classList.contains('showCart')) {
    body.classList.add('showCart');
  }
}

// document.querySelector('.checkOut').addEventListener('click', function() {
//   const totalAmount = Object.values(cart).reduce((acc, item) => acc + item.price * item.quantity, 0);
//   window.location.href = `payment.html?amount=${totalAmount}`;
// });

document.querySelector('.checkOut').addEventListener('click', function() {
  const totalAmount = Object.values(cart).reduce((acc, item) => acc + item.price * item.quantity, 0);
  
  // Collect product details
  const products = Object.values(cart).map(item => ({
    name: item.name,
    quantity: item.quantity
  }));

  // Store products in local storage
  localStorage.setItem('cartProducts', JSON.stringify(products));

  window.location.href = `../Dynamic/payment.jsp?amount=${totalAmount}`;
});

