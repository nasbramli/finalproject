// save temporary data to local storage
//  ----------- area to invoke the functions ----------- 

// temporary data
var data = [];

// Create instance of productList, i.e. controller class
const productController = new Controller()

// Fetch data from API
async function fetchData() {
    try {
        let response = await fetch("/products");
        let data = await response.json();
        console.log(data);
        productController.displayCart(data);
    }
    catch (error) {
        console.error("Error fetching products from API: ", error);
    }
}
// Initial fetch - Load immediately when the script is loaded to fetch initial data.
fetchData();


// Initialize a new TaskManager with currentId set based on the length of 'data' (number of elements in data)
//const productsController = new Controller(data.length, data);
//productsController.loadDataFromLocalStorage();


