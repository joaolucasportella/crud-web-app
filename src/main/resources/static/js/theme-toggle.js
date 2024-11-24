document.getElementById("theme-toggle").addEventListener("click", function () {
  var body = document.getElementById("page-body");
  var mainContent = document.getElementById("main-content");
  var listGroupItems = document.querySelectorAll(".list-group-item");
  var links = document.querySelectorAll(".list-group-item a");

  if (body.classList.contains("bg-dark")) {
    body.classList.remove("bg-dark", "text-light");
    body.classList.add("bg-light", "text-dark");
    mainContent.classList.remove("bg-dark", "text-light");
    mainContent.classList.add("bg-light", "text-dark");

    listGroupItems.forEach(function (item) {
      item.classList.remove("bg-dark", "text-light");
      item.classList.add("bg-light", "text-dark");
    });

    links.forEach(function (link) {
      link.classList.remove("text-light");
      link.classList.add("text-dark");
    });
  } else {
    body.classList.remove("bg-light", "text-dark");
    body.classList.add("bg-dark", "text-light");
    mainContent.classList.remove("bg-light", "text-dark");
    mainContent.classList.add("bg-dark", "text-light");

    listGroupItems.forEach(function (item) {
      item.classList.remove("bg-light", "text-dark");
      item.classList.add("bg-dark", "text-light");
    });

    links.forEach(function (link) {
      link.classList.remove("text-dark");
      link.classList.add("text-light");
    });
  }
});
