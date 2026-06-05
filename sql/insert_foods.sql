USE rms_db;

-- Clear any existing foods to prevent duplicate keys and reset ordering
TRUNCATE TABLE foods;

-- Insert all 37 food items with descriptions
INSERT INTO foods (food_name, food_price, food_category, food_img, food_desc) VALUES
('Cheese Pizza', 150, 'Top Dishes', 'uploads/Chesse Pizza.jpg', 'Delicious pizza loaded with extra mozzarella cheese and fresh herbs.'),
('Chicken Biryani', 220, 'Dinner', 'uploads/Chicken Biriyani.jpg', 'Fragrant long-grain basmati rice layered with spiced marinated chicken.'),
('Chicken Butter Masala & Paratha', 180, 'Dinner', 'uploads/Chicken Butter Masala & Paratha.png', 'Creamy tomato-based butter chicken served with hot flaky parathas.'),
('Chicken Cheese Burger', 120, 'Top Dishes', 'uploads/Chicken Chess Burgur.jpg', 'Juicy grilled chicken patty with cheddar cheese, lettuce, and burger sauce.'),
('Chicken Fried Wings', 140, 'Top Dishes', 'uploads/Chicken Fried Wings.jpg', 'Crispy, deep-fried chicken wings coated in a savory seasoning mix.'),
('Chicken Momo', 90, 'Top Dishes', 'uploads/Chicken Momo.jpg', 'Delicious steamed dumplings stuffed with juicy minced chicken and spices.'),
('Chicken Roll', 70, 'Breakfast', 'uploads/Chicken Rolls.jpg', 'Flaky flatbread wrap filled with sautéed spiced chicken, onions, and sauce.'),
('Chinese Mix Rice & Broccoli', 160, 'Lunch', 'uploads/Chinese Mix Rice & broccoli.jpg', 'Stir-fried Chinese rice tossed with fresh broccoli and mixed seasonal vegetables.'),
('Chocolate Shake', 100, 'Breakfast', 'uploads/Chocolate Shake.jpg', 'Rich and creamy milkshake blended with premium chocolate syrup and ice cream.'),
('Chole Bhatura', 90, 'Breakfast', 'uploads/Chole Bhatura.jpeg', 'Traditional Punjabi spiced chickpea curry served with fluffy deep-fried bhaturas.'),
('Doughnut', 60, 'Breakfast', 'uploads/Doughnut.jpg', 'Soft and sweet glazed doughnut topped with colorful sugar sprinkles.'),
('Egg Chowmein', 100, 'Breakfast', 'uploads/Egg Chowmin.jpg', 'Wok-tossed noodles with scrambled eggs, onions, cabbage, and soy sauce.'),
('Indian Thali', 180, 'Lunch', 'uploads/Indian Thali.jpg', 'A complete meal featuring rice, dal, subji, roti, salad, and sweet pickle.'),
('Masala Dosa', 80, 'Breakfast', 'uploads/Masala Dosha.webp', 'Crispy rice crepe filled with a savory spiced potato and onion mash.'),
('Milk Shake', 90, 'Breakfast', 'uploads/Milk Shake.jpg', 'Classic vanilla flavored sweet cold milk shake topped with whipped cream.'),
('Mix Chicken Thali', 200, 'Lunch', 'uploads/Mix Chicken Thali.jpg', 'Thali featuring chicken curry, seasonal vegetables, dal, rice, and roti.'),
('Mix Pasta', 110, 'Top Dishes', 'uploads/Mix Pasts.jpg', 'Penne pasta tossed in a rich blend of red marinara and white cheese sauce.'),
('Muesli & Milk', 70, 'Breakfast', 'uploads/Muesli & Milk.jpg', 'Healthy breakfast bowl with crispy muesli cereal served with cold milk.'),
('Mutton Biryani Special', 280, 'Dinner', 'uploads/Mutton Biriyani.webp', 'Royal mutton biryani with aromatic rice, tender mutton pieces, and spices.'),
('Non Veg Thali', 220, 'Lunch', 'uploads/Non Veg Thali.jpg', 'Sumptuous lunch thali featuring fish curry, chicken subji, dal, and rice.'),
('Paneer Pasanda', 160, 'Dinner', 'uploads/Paneer Pasinda.jpg', 'Rich paneer dish stuffed with nuts and raisins, served in a creamy gravy.'),
('Peri Peri Pasta', 130, 'Top Dishes', 'uploads/Peri Peri Pasta.jpg', 'Spicy pasta tossed in a hot and tangy peri-peri seasoning and red sauce.'),
('Plain Dosa', 60, 'Breakfast', 'uploads/Plain Dosha.jpg', 'Thin, crispy plain rice-and-lentil crepe served with sambar and coconut chutney.'),
('Polao Rice', 120, 'Dinner', 'uploads/Polao Rice.jpg', 'Sweet and fragrant yellow rice cooked with ghee, cashews, and raisins.'),
('Rice & Chicken Meatballs', 170, 'Lunch', 'uploads/Rice & Chicken Meatballs.jpg', 'Steamed white rice paired with juicy chicken meatballs in a savory gravy.'),
('Sandwich & Sauce', 60, 'Breakfast', 'uploads/Sandwich & Sause.jpg', 'Toasted vegetable club sandwich served with tangy tomato ketchup.'),
('Special Chicken Biryani', 250, 'Dinner', 'uploads/Special Chicken Biriyani.png', 'Our chef\'s special double-spiced chicken biryani served with a boiled egg.'),
('Steam Momo', 70, 'Top Dishes', 'uploads/Steam Momo.jpg', 'Hot steamed vegetarian dumplings served with spicy garlic-tomato chutney.'),
('Toast', 40, 'Breakfast', 'uploads/Toast.jpg', 'Butter toasted white bread slices, perfect with a hot cup of tea or coffee.'),
('Veg Chowmein', 80, 'Breakfast', 'uploads/Veg Chowmin.jpg', 'Stir-fried noodles with crisp vegetables like carrots, cabbage, and bell peppers.'),
('Veg Fried Rice', 110, 'Lunch', 'uploads/Veg Fried Rice.jpg', 'Fragrant basmati rice wok-tossed with finely chopped carrots, beans, and corn.'),
('Veg Thali', 150, 'Lunch', 'uploads/Veg Thali.jpg', 'Healthy traditional thali with dal fry, paneer subji, jeera rice, and roti.'),
('Pepperoni Cheese Pizza', 170, 'Top Dishes', 'uploads/pepperoni Chesse Pizza.jpg', 'Classic pizza topped with spicy pepperoni slices and melted mozzarella.'),
('Bengali Thali', 250, 'Lunch', 'uploads/Bengali-thali.jpg', 'Authentic Bengali meal featuring Shorshe Maach (mustard fish), dal, and rice.'),
('Mutton Biryani', 320, 'Dinner', 'uploads/Mutton-Biriyani.jpg', 'Premium double-portion mutton biryani slow-cooked in traditional Dum style.'),
('Paneer Pizza', 180, 'Top Dishes', 'uploads/paneer-pizza.webp', 'Wood-fired thin crust pizza topped with fresh paneer cubes and capsicum.'),
('Coffee', 80, 'Breakfast', 'uploads/Coffee.avif', 'Freshly brewed aromatic hot espresso made from rich dark roasted coffee beans.');
