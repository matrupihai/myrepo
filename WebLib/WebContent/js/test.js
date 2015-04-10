var url = "http://localhost:8080/WebLib/authors";

$("document").ready(
	findAll()
);

function findAll() {
	console.log("findAll");
	$.ajax({
		type: "GET",
		url: url,
		dataType: "json", 
		success: renderList
	});
}

function renderList(data) {
	var newData = data == null ? [] : (data instanceof Array ? data : [data]);
	var list = '';
	$.each(newData, function(i, author) {
		list += "<li>" + author.authorName + "</li>";
	});
	$("#authorsList").append(list);
}







