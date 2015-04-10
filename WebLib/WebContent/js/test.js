var booksUrl = "http://localhost:8080/WebLib/books";

$("document").ready(
	findAllBooks()
);

function findAllBooks() {
	console.log("findAll");
	$.ajax({
		type: "GET",
		url: booksUrl,
		dataType: "json", 
		success: renderList
	});
}

function renderBooksList(data) {
	var newData = data == null ? [] : (data instanceof Array ? data : [data]);
	var list = '';
	$.each(newData, function(i, author) {
		list += "<li>" + author.authorName + "</li>";
	});
	$("document").append(list);
}







