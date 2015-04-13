
var booksUrl = "http://localhost:8080/WebLib/books";
var loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
"Suspendisse dapibus cursus eros. Nunc euismod nisl sed dapibus feugiat. " +
"Nullam at metus et turpis dictumelementum.";

$("document").ready(
		findAllBooks()
);

function findAllBooks() {
	console.log("findAllBooks");
	$.ajax({
		type: "GET",
		url: booksUrl,
		dataType: "json", 
		success: renderBooksList
	});
}

function renderBooksList(data) {
	var newData = data == null ? [] : (data instanceof Array ? data : [data]);
	$.each(newData, function(i, book) {
		$(".booksContainer").append(renderBook(book));
	});
}

function renderBook(book) {
	var bookString = "";
	var bookAuthors = [];
	
	$.each(book.authors, function(i, author) {
		bookAuthors.push(author.authorName);
	});
	
	bookString += "<div class=\"bookCard\">";
	bookString += "<div class=\"bookImg\">";
	bookString += "<img alt=\"bookImg\" src=\"img/imgStub.png\" />";
	bookString += "</div>";
	bookString += "<div class=\"bookHeader\">";
	bookString += "<h1 class=\"bookTitle\">" + book.title + "</h1>";
	bookString += "<p class=\"bookAuthor\">" + bookAuthors.toString();
	bookString += "<span class=\"bookPages\">" + book.noOfPages + " pages</span></p></div>";
	bookString += "<p class=\"bookDescription\">" + loremIpsum + "</p>";
	bookString += "<div class=\"bookActions\"></div></div>";
	
	return bookString;
}

