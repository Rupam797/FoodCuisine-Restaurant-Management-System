package org.apache.jsp.Dynamic;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class processbooking_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <title>Table Booking</title>\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            font-family: Arial, sans-serif;\n");
      out.write("            margin: 20px;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .container {\n");
      out.write("            display: flex;\n");
      out.write("            flex-wrap: wrap;\n");
      out.write("            justify-content: center;\n");
      out.write("            gap: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .table {\n");
      out.write("            width: 100px;\n");
      out.write("            height: 100px;\n");
      out.write("            display: flex;\n");
      out.write("            align-items: center;\n");
      out.write("            justify-content: center;\n");
      out.write("            border: 2px solid #4CAF50;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            background-color: #e0ffe0;\n");
      out.write("            color: #000;\n");
      out.write("            font-weight: bold;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .table.small {\n");
      out.write("            background-color: #f0f8ff;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .table.medium {\n");
      out.write("            background-color: #ffe4b5;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .table.large {\n");
      out.write("            background-color: #d8bfd8;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .table.booked {\n");
      out.write("            background-color: red;\n");
      out.write("            color: white;\n");
      out.write("            cursor: not-allowed;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        #message {\n");
      out.write("            margin-top: 20px;\n");
      out.write("            font-size: 1.2em;\n");
      out.write("            color: red;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("    <script>\n");
      out.write("        document.addEventListener(\"DOMContentLoaded\", () => {\n");
      out.write("            const tables = document.querySelectorAll(\".table\");\n");
      out.write("            const message = document.getElementById(\"message\");\n");
      out.write("\n");
      out.write("            tables.forEach(table => {\n");
      out.write("                table.addEventListener(\"click\", () => {\n");
      out.write("                    if (!table.classList.contains(\"booked\")) {\n");
      out.write("                        table.classList.add(\"booked\");\n");
      out.write("                        checkAllReserved();\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("\n");
      out.write("            function checkAllReserved() {\n");
      out.write("                const allBooked = Array.from(tables).every(table => table.classList.contains(\"booked\"));\n");
      out.write("                if (allBooked) {\n");
      out.write("                    message.textContent = \"All tables are reserved.\";\n");
      out.write("                    alert(\"All tables are reserved\");\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        });\n");
      out.write("    </script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <h1>Table Booking</h1>\n");
      out.write("    <p>Select your table (Small, Medium, Large). Booked tables will be marked in red.</p>\n");
      out.write("\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <!-- Small Tables -->\n");
      out.write("        <div class=\"table small\" data-size=\"small\">Small</div>\n");
      out.write("        <div class=\"table small\" data-size=\"small\">Small</div>\n");
      out.write("\n");
      out.write("        <!-- Medium Tables -->\n");
      out.write("        <div class=\"table medium\" data-size=\"medium\">Medium</div>\n");
      out.write("        <div class=\"table medium\" data-size=\"medium\">Medium</div>\n");
      out.write("\n");
      out.write("        <!-- Large Tables -->\n");
      out.write("        <div class=\"table large\" data-size=\"large\">Large</div>\n");
      out.write("        <div class=\"table large\" data-size=\"large\">Large</div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div id=\"message\"></div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
