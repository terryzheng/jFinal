<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.zhaopin.models.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>users jsp</title>
</head>
<body>
	<table>
		<%
			List<HashMap> ul = (List<HashMap>) request.getAttribute("ul");
			if (ul != null) {
				Iterator<HashMap> iter = ul.iterator();
				while (iter.hasNext()) {
					HashMap hm = iter.next();
		%>
		<tr>
			<td><%=hm.get("usr_name").toString().split("@")[0]%></td>
			<td><%=hm.get("usr_email")%></td>
		</tr>
		<%
			}
			}
		%>
	</table>
</body>
</html>