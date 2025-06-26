const deleteBtns = document.querySelectorAll(".deleteBtn")
const deleteModal = document.getElementById("deleteModal")
const saveBtn = document.getElementById("saveBtn")


deleteBtns.forEach(deleteBtn => {
  deleteBtn.addEventListener("click", () => {

    console.log("clicked")


    deleteModal.classList.remove("d-none")

  })
})
