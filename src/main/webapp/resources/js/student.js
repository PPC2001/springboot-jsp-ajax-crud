$(document).ready(function() {
	console.log("i am ready");
	getAllStudent();
});

function getAllStudent() {
	$('#studentTable').dataTable().fnDestroy();
	$
		.ajax({
			type: "GET",
			url: "http://localhost:6060/api/student",
			dataType: "json",
			success: function(data) {
				$('#studentTable')
					.dataTable(
						{
							data: data,
							columns: [
								{
									data: 'name'
								},
								{
									data: 'email'
								},
								{
									data: 'phone'
								},
								{
									data: 'userName'
								},
								{
									data: 'password'
								},
								{
									data: null,
									render: function(
										data, type,
										full, meta) {
										return '<button class="btn btn-success btnEdit" id="btnEdit"  value="' + data.id + '" >Edit</button>';
									}
								},
								{
									data: null,
									render: function(
										data, type,
										full, meta) {
										return '<button class="btn btn-danger btnEdit" id="btnDelete"  value="' + data.id + '" >Delete</button>';
									}
								}

							]
						});
			}
		});
}

function addStudent() {
	this.event.preventDefault();
	let name = $('#name').val();
	let email = $('#email').val();
	let phone = $('#phone').val();
	let userName = $('#userName').val();
	let password = $('#password').val();

	if (name == "" || email == "" || phone == "" || userName == "" || password == "") {
		Swal.fire("Kindy Enter the details");
		return false;
	}
	if (password.length < 8) {
		Swal.fire("Password must be at least 8 characters");
		return false; // Prevent form submission
	}

	let student = {
		name: name,
		email: email,
		phone: phone,
		userName: userName,
		password: password
	};

	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "http://localhost:6060/api/student",
		data: JSON.stringify(student),
		dataType: "json",
		success: function(result) {
			getAllStudent();
			Swal.fire({
				icon: 'success',
				title: 'Data saved successfully!',
				showConfirmButton: false,
				timer: 1500
			});
			$('#name').val('');
			$('#email').val('');
			$('#phone').val('');
			$('#userName').val('');
			$('#password').val('');
		},
		error: function(error) {
			Swal.fire({
				icon: 'error',
				title: 'Oops...',
				text: 'Something went wrong!',
			});
		}
	});

}

$(document).on('click', '#btnEdit', function(e) {
	e.preventDefault();
	var id = $(this).val();
	console.log(id);
	$('#exampleModal').modal('show');
	$.ajax({
		type: "GET",
		url: "http://localhost:6060/api/edit/" + id,
		dataType: "JSON",
		success: function(data) {
			console.log(data);
			$('#id_edit').val(data.id);
			$('#name_edit').val(data.name);
			$('#email_edit').val(data.email);
			$('#phone_edit').val(data.phone);
			$('#userName_edit').val(data.userName);
			$('#password_edit').val(data.password);
		}
	});
});

$(document).on('click', '#update', function(e) {
	e.preventDefault();
	var id = $('#id_edit').val();
	var data = {
		"name": $('#name_edit').val(),
		"email": $('#email_edit').val(),
		"phone": $('#phone_edit').val(),
		"userName": $('#userName_edit').val(),
		"password": $('#password_edit').val()
	}
	console.log(data);
	$.ajax({
		type: "PUT",
		url: "http://localhost:6060/api/edit/" + id,
		contentType: "application/json",
		data: JSON.stringify(data),
		dataType: "JSON",
		success: function(response) {
			console.log(response);
			$('#exampleModal').modal('hide');
			getAllStudent();
			Swal.fire({
				icon: 'success',
				title: 'Data Updated successfully!',
				showConfirmButton: false,
				timer: 1500
			});
		}
	});
});

$(document).on('click', '#btnDelete', function(e) {
	e.preventDefault();
	var id = $(this).val();
	$.ajax({
		type: "DELETE",
		url: "http://localhost:6060/api/delete/" + id,
		success: function(response) {
			getAllStudent();
			Swal.fire({
				icon: 'success',
				title: 'Data deleted successfully!',
				showConfirmButton: false,
				timer: 1500
			});
		}
	});
});