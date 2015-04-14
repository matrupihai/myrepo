
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
	var bookAuthors = [];
	
	$.each(book.authors, function(i, author) {
		bookAuthors.push(author.authorName);
	});
	
	var bookCard = $("<div/>", {
		class: "bookCard"
	});
	
	var bookHeader = $("<div/>", {
		class: "bookHeader"
	});
	
	var bookTitle = $("<h1/>", {
		class: "bookTitle",
		html: book.title
	});
	
	var bookAuthor = $("<p/>", {
		class: "bookAuthor",
		html: bookAuthors.toString()
	});
	
	var bookPages = $("<span/>", {
		class: "bookPages",
		html: book.noOfPages + " pages"
	});
	
	var bookImg = $("<div/>", {
		class: "bookImg",
		html: "<img alt=\"bookImg\" src=\"img/imgStub.png\" />"
	});
	
	var bookDescription = $("<p/>", {
		class: "bookDescription",
		html: loremIpsum
	});
	
	var bookActions = $("<div/>", {
		class: "bookActions"
	});
	
	var borrowBook = $("<a/>", {
		id: "borrowBook",
		href: "#",
		html: "Borrow"
	});
	
	bookImg.appendTo(bookCard);
	bookHeader.appendTo(bookCard);
	bookTitle.appendTo(bookHeader);
	bookAuthor.appendTo(bookHeader);
	bookPages.appendTo(bookAuthor);
	bookDescription.appendTo(bookCard);
	bookActions.appendTo(bookCard);
	borrowBook.appendTo(bookActions);
	jQuery.data(bookCard, book);
	
	return bookCard;
}

$("document").ready(function() {
	$("#borrowBook").click(function(event) {
		alert("Hello!");
		var book = $(this).closest(".bookCard").data(); 
		alert(book.yearPublished);
	});
});









