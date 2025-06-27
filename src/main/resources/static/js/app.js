const deleteBtns = document.querySelectorAll(".deleteBtn")
const deleteModal = document.querySelector(".deleteModal")
const closeBtn = document.getElementById("closeBtn")
const confirmMsg = document.getElementById("confirmMsg")

deleteBtns.forEach(deleteBtn => {
  deleteBtn.addEventListener("click", () => {

    console.log("clicked")

    const id = deleteBtn.dataset.pizzaid;


    const pizzaName = deleteBtn.dataset.pizzaname;

    console.log(pizzaName)

    const deleteForm = document.querySelector(".deleteForm")

    deleteForm.action = `/pizzas/delete/${id}`

    confirmMsg.innerHTML = `Are you sure you want to delete: ${pizzaName} Pizza?`

    deleteModal.classList.remove("d-none")



  })
})


closeBtn.addEventListener("click", () => {

  console.log("clicked")
  console.log(deleteModal.classList)
  deleteModal.classList.add("d-none")
  console.log(deleteModal.classList)

})

