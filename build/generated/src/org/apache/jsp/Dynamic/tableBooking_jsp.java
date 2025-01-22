package org.apache.jsp.Dynamic;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class tableBooking_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Table Booking</title>\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            font-family: Arial, sans-serif;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("        .screen {\n");
      out.write("            width: 80%;\n");
      out.write("            margin: 20px auto;\n");
      out.write("            padding: 10px;\n");
      out.write("            background: #ccc;\n");
      out.write("            color: black;\n");
      out.write("            text-align: center;\n");
      out.write("            font-weight: bold;\n");
      out.write("        }\n");
      out.write("        .table-layout {\n");
      out.write("            display: grid;\n");
      out.write("            grid-template-columns: repeat(5, 80px);\n");
      out.write("            gap: 10px;\n");
      out.write("            justify-content: center;\n");
      out.write("            margin: 20px;\n");
      out.write("        }\n");
      out.write("        .table {\n");
      out.write("            width: 80px;\n");
      out.write("            height: 80px;\n");
      out.write("            background-color: #f0f0f0;\n");
      out.write("            border: 2px solid #ccc;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            text-align: center;\n");
      out.write("            line-height: 80px;\n");
      out.write("            cursor: pointer;\n");
      out.write("        }\n");
      out.write("        .table.selected {\n");
      out.write("            background-color: #6c7ae0;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("        .table.booked {\n");
      out.write("            background-color: #ff4d4d;\n");
      out.write("            color: white;\n");
      out.write("            cursor: not-allowed;\n");
      out.write("        }\n");
      out.write("        .legend {\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: center;\n");
      out.write("            gap: 20px;\n");
      out.write("            margin: 20px;\n");
      out.write("        }\n");
      out.write("        .legend-item {\n");
      out.write("            display: flex;\n");
      out.write("            align-items: center;\n");
      out.write("            gap: 5px;\n");
      out.write("        }\n");
      out.write("        .legend-item span {\n");
      out.write("            width: 20px;\n");
      out.write("            height: 20px;\n");
      out.write("            display: inline-block;\n");
      out.write("            border: 1px solid black;\n");
      out.write("            border-radius: 5px;\n");
      out.write("        }\n");
      out.write("        .available { background-color: #f0f0f0; }\n");
      out.write("        .selected { background-color: #6c7ae0; }\n");
      out.write("        .booked { background-color: #ff4d4d; }\n");
      out.write("    </style>\n");
      out.write("    <script>\n");
      out.write("        document.addEventListener(\"DOMContentLoaded\", () => {\n");
      out.write("            const tables = document.querySelectorAll(\".table\");\n");
      out.write("\n");
      out.write("            tables.forEach(table => {\n");
      out.write("                table.addEventListener(\"click\", () => {\n");
      out.write("                    if (table.classList.contains(\"booked\")) return;\n");
      out.write("\n");
      out.write("                    table.classList.toggle(\"selected\");\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("\n");
      out.write("            document.getElementById(\"confirmBooking\").addEventListener(\"click\", () => {\n");
      out.write("                const selectedTables = [];\n");
      out.write("                tables.forEach(table => {\n");
      out.write("                    if (table.classList.contains(\"selected\")) {\n");
      out.write("                        selectedTables.push(table.dataset.tableId);\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("\n");
      out.write("                if (selectedTables.length > 0) {\n");
      out.write("                    alert(\"Selected Tables: \" + selectedTables.join(\", \"));\n");
      out.write("                    // Pass selected data to the backend using a form or AJAX\n");
      out.write("                    document.getElementById(\"selectedTables\").value = selectedTables.join(\",\");\n");
      out.write("                    document.getElementById(\"bookingForm\").submit();\n");
      out.write("                } else {\n");
      out.write("                    alert(\"Please select at least one table.\");\n");
      out.write("                }\n");
      out.write("            });\n");
      out.write("        });\n");
      out.write("    </script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <h1>Table Booking</h1>\n");
      out.write("    <div class=\"screen\">Restaurant Area</div>\n");
      out.write("\n");
      out.write("    <div class=\"table-layout\">\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
 for (int i = 1; i <= 20; i++) { 
      out.write("\n");
      out.write("            ");
      out.write("\n");
      out.write("            <div class=\"table ");
      out.print( (i == 3 || i == 7) ? "booked" : "" );
      out.write("\" data-table-id=\"");
      out.print( i );
      out.write("\">\n");
      out.write("                Table ");
      out.print( i );
      out.write("\n");
      out.write("            </div>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div class=\"legend\">\n");
      out.write("        <div class=\"legend-item\"><span class=\"available\"></span> Available</div>\n");
      out.write("        <div class=\"legend-item\"><span class=\"selected\"></span> Selected</div>\n");
      out.write("        <div class=\"legend-item\"><span class=\"booked\"></span> Booked</div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <button id=\"confirmBooking\">Confirm Booking</button>\n");
      out.write("\n");
      out.write("    ");
      out.write("\n");
      out.write("    <form id=\"bookingForm\" action=\"/processBooking.jsp\" method=\"POST\" style=\"display: none;\">\n");
      out.write("        <input type=\"hidden\" name=\"selectedTables\" id=\"selectedTables\">\n");
      out.write("    </form>\n");
      out.write("</body>\n");
      out.write("</html>");
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
