package cartpack;



import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import cartpack.Cart;
import cartpack.CartItem;

public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int foodId = Integer.parseInt(request.getParameter("foodId"));
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        
        try {
            // Database connection
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");
            String query = "SELECT FOOD_NAME, FOOD_PRICE, FOOD_IMG FROM FOODS WHERE FOOD_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, foodId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String name = rs.getString("FOOD_NAME");
                int price = rs.getInt("FOOD_PRICE");
                String image = rs.getString("FOOD_IMG");
                
                // Add item to cart
                CartItem item = new CartItem(foodId, name, price, image, 1); // 1 is the initial quantity
                cart.addItem(item);
            }
            
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("/Dynamic/menu.jsp");

    }
}

