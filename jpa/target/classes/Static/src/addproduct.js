// Initialize a new ItemsController with currentId set to 0
const productsController = new Controller();

// Select the New Item Form
const newProductForm = document.querySelector('#productForm');

// Add an 'onsubmit' event listener
newProductForm.addEventListener('submit', (event) => {
    // Prevent default action
    event.preventDefault();

    // Select the inputs
    const newProductTitle = document.querySelector('#productTitle');
    const newProductContent = document.querySelector('#productContent');
    const newProductImageUrl = document.querySelector('#productImageUrl');    

    // Get the values of the inputs
    const title = newProductTitle.value;
    const content = newProductContent.value;
    const image = newProductImageUrl.value;

    /*
        Validation code here
    */

    // Add the item to the ItemsController
    productsController.sendJSON(title, content, image);

    // Bootstrap 5 inclusions
    // Run BootStrap5's toast to show the activity is complete.
    var toastEl = document.querySelector('.toast');
    var toast = new bootstrap.Toast(toastEl);
    toast.show();

    // Clear the form
    newProductTitle.value = '';
    newProductContent.value = '';   
    newProductImageUrl.value = '';    
});

// Bootstrap 5 inclusions
var productAddedToast = document.querySelector('.toast');
productAddedToast.addEventListener('hidden.bs.toast', function () {
      window.open("cards.html", "productlistwindow");
})