package adminpack;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

@MultipartConfig
public class AddFood extends HttpServlet {

    // Declare variables to hold food data
    int id, price;
    String name, quantity, img, category;
    
    // Declare Oracle objects
    OracleConnection oconn;
    OraclePreparedStatement ops;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println(request.getParameter("fid"));
            out.println(request.getParameter("foodname"));
            out.println(request.getParameter("foodprice"));
            //out.println(request.getParameter("foodimg"));
            out.println(request.getParameter("foodcategory"));

            // Get the parameters from the request (the form fields sent by the admin)
            id = Integer.parseInt(request.getParameter("fid"));
            name = request.getParameter("foodname");
            quantity = request.getParameter("foodquantity");
            price = Integer.parseInt(request.getParameter("foodprice"));
  //        img = request.getParameter("foodimg"); // Assuming this is a URL or file path for the image
            category = request.getParameter("foodcategory");
            Part avatar_image = request.getPart("foodimg");
            out.println(avatar_image);
            
            
            
                String uploadDir = "uploads";
                String avatarFileName = name + "" + getFileExtension(avatar_image.getSubmittedFileName());

                // Ensuring the upload directory exists
               String uploadPath = "C:\\RMS\\build\\web\\uploads";
                System.out.println("uploads path : " + uploadPath);
                File uploadDirectory = new File(uploadPath);
                if (!uploadDirectory.exists()) {
                    uploadDirectory.mkdir();
                }

                // Full path to save the file
                String fullPath = uploadPath + File.separator + avatarFileName;
                System.out.println(fullPath);
                try {
                    avatar_image.write(fullPath);
                    System.out.println("File written successfully to: " + fullPath);
                } catch (IOException e) {
                    e.printStackTrace();
                    out.println("File write failed: " + e.getMessage());
                }
                out.println("Full path : " + fullPath);

                String avatarPath = uploadDir + "/" + avatarFileName;

            // Register Oracle JDBC Driver
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

            // Establish a connection to the database
            oconn = (OracleConnection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");

            if (oconn != null) {

                // Prepare SQL Query to insert food details into the database
                String sql = "INSERT INTO FOODS(FOOD_ID, FOOD_NAME, FOOD_PRICE, FOOD_CATEGORY,FOOD_IMG) VALUES (?, ?, ?, ?, ?)";
                ops = (OraclePreparedStatement) oconn.prepareStatement(sql);

                // Set the values for the query parameters
                ops.setInt(1, id);
                ops.setString(2, name);
                ops.setInt(3, price);
                ops.setString(4, category);
                ops.setString(5, avatarPath);

                // Execute the query
                int rowsInserted = ops.executeUpdate();

                // Check if the record was inserted successfully
                if (rowsInserted > 0) {
                    // If successful, redirect the admin to a confirmation or success page
                    out.println("<html><body><h3>Food item added successfully!</h3></body></html>");
                    response.sendRedirect("/Dynamic/FoodDetails.jsp"); // Redirect to a page where the admin can view the food items
                } else {
                    // If insertion fails, show an error message
                    out.println("<html><body><h3>Error adding food item!</h3></body></html>");
                }
            }

        } catch (SQLException ex) {
            // Handle SQL exceptions
            ex.printStackTrace();
            try (PrintWriter out = response.getWriter()) {
                out.println("<html><body><h3 style='color:red;'>Database Error: " + ex.getMessage() + "</h3></body></html>");
            }
        } finally {
            // Close resources
            try {
                if (ops != null) {
                    ops.close();
                }
                if (oconn != null) {
                    oconn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // Utility method to get file extension
    private String getFileExtension(String fileName) {
        if (fileName == null) {
            return "";
        }
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex >= 0) ? fileName.substring(dotIndex) : "";
    }
}
