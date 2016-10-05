<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/paginas/css/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
	<body>
		<form class="form-horizontal" action="" method="post">
		    <label class="col-sm-2 control-label" for="test">Test</label>
		    <div class="col-sm-10">
		        <input id="test" type="text" class="form-control" disabled/>
		    </div>
		    <label class="col-sm-2 control-label" for="more">More</label>
		    <div class="col-sm-10">
		        <input id="more" type="text" class="form-control" disabled/>
		    </div>
		</form>
	</body>
</html>