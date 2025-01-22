<%@ page import="java.sql.DriverManager"%>
<%@ page import="oracle.jdbc.OracleResultSetMetaData"%>
<%@ page import="oracle.jdbc.OracleResultSet"%>
<%@ page import="oracle.jdbc.OraclePreparedStatement"%>
<%@ page import="oracle.jdbc.OracleConnection"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Displaying Records from Oracle</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7f6;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #4CAF50;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }

        th, td {
            padding: 12px 20px;
            text-align: left;
            font-size: 14px;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        button {
            padding: 8px 16px;
            margin: 5px;
            border: none;
            cursor: pointer;
            font-size: 14px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        button.edit {
            background-color: #4CAF50;
            color: white;
        }

        button.edit:hover {
            background-color: #45a049;
        }

        button.delete {
            background-color: #f44336;
            color: white;
        }

        button.delete:hover {
            background-color: #da190b;
        }

        tfoot {
            background-color: #f9f9f9;
            text-align: center;
            font-size: 12px;
        }

        tfoot th {
            padding: 10px 0;
            color: #777;
        }

    </style>
    <%!
        // STEP 3: DECLARING OBJECTS AND VARIABLES
        OracleConnection oconn;
        OraclePreparedStatement ops;
        OracleResultSet ors;
        OracleResultSetMetaData orsmd;
        int counter, reccounter, colcounter;
    %>
    <%
    // STEP 4: REGISTRATION OF ORACLE DRIVER
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

    // STEP 5: INSTANTIATING THE CONNECTION
    oconn = 
            (OracleConnection)
            DriverManager.getConnection
    ("jdbc:oracle:thin:@localhost:1521:orcl", "RUPAM", "GIRI");

    // STEP 6: INSTANTIATING THE STATEMENT OBJECT
    ops = (OraclePreparedStatement)oconn.prepareCall("SELECT * FROM BOOKING ORDER BY BOOKING_DATE");

    // STEP 7: FILLING UP THE DATABASE RECORDS IN A TEMPORARY CONTAINER
    ors = (OracleResultSet)ops.executeQuery();

    // STEP 8: GETTING THE COLUMNS INFORMATION(METADATA)
    orsmd = (OracleResultSetMetaData)ors.getMetaData();
    %>
</head>
<body>
    <h1>Table Booking Details</h1>
    <!-- STEP 1: BASIC STRUCTURE OF A TABLE -->
    <table>
        <thead>
            <tr>
                <%
                    // STEP 9: BRINGING THE TABLE HEADINGS
                    for(counter = 1; counter <= orsmd.getColumnCount(); counter++)
                    {
                %>
                <th><%=orsmd.getColumnName(counter)%></th>
                <%
                    }
                %>
                <th>ACTION</th>
            </tr>
        </thead>
        <tbody>
        <%
            // STEP 10: BRINGING ALL THE RECORDS AND DISPLAYING AS TABLE ROWS
            while(ors.next() == true)
            {
        %>
        <tr>
            <%
               for(counter = 1; counter <= orsmd.getColumnCount(); counter++)
                {
            %>
            <td><%=ors.getString(counter)%></td>
            <%
                }
            %>
            <td>
                <!-- DELETE FORM -->
                <form action="/DeleteBookingServlet" method="post" style="display:inline;">
                    <input type="hidden" name="bookingId" value="<%=ors.getString("BOOKING_ID")%>">
                    <button class="delete" type="submit">Delete</button>
                </form>

                <!-- EDIT FORM -->
                <form action="/Dynamic/EditBooking.jsp" method="get" style="display:inline;">
                    <input type="hidden" name="bookingId" value="<%=ors.getString("BOOKING_ID")%>">
                    <button class="edit" type="submit">Edit</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
        <tfoot>
            <tr>
                <th colspan="10">End of Records</th>
            </tr>
        </tfoot>
    </table>

    <%
    // STEP 11: CLOSING THE CONNECTIONS
    ors.close();
    ops.close();
    oconn.close();
    %>

</body>
</html>
