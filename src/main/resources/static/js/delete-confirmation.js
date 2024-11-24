function confirmDelete(event, button) {
  var customerId = button.getAttribute("data-id");

  var confirmation = confirm("Você tem certeza que deseja excluir este cliente?");

  if (confirmation) {
    window.location.href = "/customer/delete/" + customerId;
  }
}