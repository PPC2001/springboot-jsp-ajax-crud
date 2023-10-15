<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link
	href="https://cdn.datatables.net/autofill/2.5.1/css/autoFill.dataTables.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://cdn.datatables.net/buttons/2.3.2/css/buttons.dataTables.min.css"
	rel="stylesheet" type="text/css" />


<style>
.error {
	color: red;
	font-style: italic;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div class="container-fluid">
			<a class="navbar-brand" href="/">Student Registration</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid p-5">
		<div class="row">
			<div class="col-md-4">
				<div class="card">
					<div class="card-body">
						<h3 class="text-center">Add Student Details</h3>
						<form id="frmStudent" name="frmStudent">
							<div class="mb-3">
								<label for="name" class="form-label">Name</label> <input
									type="text" class="form-control" id="name"
									aria-describedby="emailHelp" placeholder="Enter your name here"
									name="name" required="required">
							</div>
							<div class="mb-3">
								<label for="email" class="form-label">Email address</label> <input
									type="email" class="form-control" aria-describedby="emailHelp"
									placeholder="Enter your email here" id="email" name="email"
									required="required">
							</div>
							<div class="mb-3">
								<label for="phone" class="form-label">Phone No.</label> <input
									type="number" class="form-control" aria-describedby="emailHelp"
									placeholder="Enter your phone number here" id="phone"
									name="phone">
							</div>
							<div class="mb-3">
								<label for="userName" class="form-label">User Name</label> <input
									type="text" class="form-control" aria-describedby="emailHelp"
									placeholder="Enter your user name here" id="userName"
									name="userName" required="required">
							</div>
							<div class="mb-3">
								<label for="password" class="form-label">Password</label> <input
									type="password" class="form-control"
									aria-describedby="emailHelp" placeholder="Enter your password"
									name="password" id="password" required="required">
							</div>
							<div class="text-center">
								<button type="button" class="btn btn-primary" id="save"
									onclick="return addStudent()">Submit</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-8">
				<div class="card">
					<div class="card-body">
						<h3 class="text-center">List of Student</h3>
						<table id="studentTable" class="table table-striped">
							<thead>
								<tr>
									<th>Student Name</th>
									<th>Email</th>
									<th>Phone</th>
									<th>UserName</th>
									<th>Password</th>
									<th>Edit</th>
									<th>Delete</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Edit Student
						Details</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="frmStudentEdit" name="frmStudentEdit">
						<div class="mb-3">
							<input type="hidden" class="form-control" id="id_edit"
								aria-describedby="emailHelp" name="id" required="required"
								readonly="readonly"> <label for="exampleInputEmail1"
								class="form-label">Name </label> <input type="text"
								class="form-control" id="name_edit" aria-describedby="emailHelp"
								name="name" required="required">

						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Email
								address</label> <input type="email" class="form-control"
								aria-describedby="emailHelp" id="email_edit" name="email"
								required="required">

						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Phone
								No.</label> <input type="number" class="form-control"
								aria-describedby="emailHelp" id="phone_edit" name="phone">

						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">User
								Name </label> <input type="text" class="form-control"
								aria-describedby="emailHelp" id="userName_edit" name="userName"
								required="required">

						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Password
							</label> <input type="password" class="form-control"
								aria-describedby="emailHelp" name="password" id="password_edit"
								required="required">
						</div>
						<button type="button" class="btn btn-primary" id="update">Update</button>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js"
		integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/autofill/2.5.1/js/dataTables.autoFill.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/buttons/2.3.2/js/dataTables.buttons.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/student.js"></script>
</body>
</html>