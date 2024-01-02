// creates a class that controls the listing of products
class Controller{

    // constructor of a class is typically used for initialisation
    // 'this' is a keyword, properties that belong to this class are declared here
    // start the current id based on the passed in value (or default to zero)
    // and store the passed-in param 'data' to localStorage via storeDataTolocalStorage

    constructor(currentId = 0, data = []){
        this.products = data !== null && data;  // class Controller's property: products
        this.currentId = currentId;             // class Controller's property: currentId

        this.storeDataToLocalStorage(data);
        
    };

    // storeDataToLocalStorage() method belongs to class Controller
    // if localStorage 'productList' doesn't exist
    // ensure there are values in 'data' before storing the data
    // current id is used here to determine the currentId in this instance of the object
    // stores the param's data into the web browser's localStorage
    // the localStorage variable is defined with the name 'productList'

    storeDataToLocalStorage(data){
        if(!localStorage.getItem("productList")){
            const sampleItems = [];
            if(data.length > 0){
                for (let index = 0; index < this.currentId; index++) {
                    sampleItems.push({
                        id: index+1,  // increment the id
                        title: data[index].title,
                        content: data[index].content,
                        image: data[index].image,
                    })
                }
            }
            localStorage.setItem("productList", JSON.stringify(sampleItems));
        }
    }

    // loadDataFromLocalStorage() method belongs to class Controller
    // loads data called 'productList' from localStorage - see helpers.js
    // after 'productList' is loaded from localStorage to local constant 'products'
    // pass the values from 'products' to method displayCart() to display the contents

    loadDataFromLocalStorage() {
        const storageItems = localStorage.getItem("productList");
        if (storageItems) {
            const products = JSON.parse(storageItems);
            this.displayCart(products);
        }
    }

    // displayCart() method belongs to class Controller
    // it receives an array of Objects
    // an instance unorderedlist is instantiated to reference classname "all-cards-container"

    // (A) in the event there are NO products
    // reset the gridTemplateColumns of class "all-cards-container"
    // and displays feedback that there are no products at the moment
    // and exit the function displayCart() prematurely - via return;

    // (B) in the event there are products
    // a for loop is used to iteratively populate class name "all-cards-container"
    // with a list of items representing each product received from param 'data'

    displayCart(data){
        const unorderedList = document.querySelector(".all-cards-container");

        // (A)
        if(!data.length){
            unorderedList.style.gridTemplateColumns = "none";
            let listItem = document.createElement("li");
            listItem.innerHTML = `<span class=\"no-products\">There are no products at the moment.</span>`
            unorderedList.appendChild(listItem);
            return;
        }

        // (B) 
        for (let index = 0; index < data.length; index++) {
            let listItem = document.createElement("li");
            listItem.className = "card-container";
            listItem.innerHTML = `
                <div class="crop">
                    <img src="${data[index].image}"/>
                </div>
                <h3>${data[index].title}</h3>
                <p>${data[index].content}</p>
            `
            unorderedList.appendChild(listItem);
        }

    };

    // addProduct() method belongs to class Controller
    // when called, addProduct instnatiates a constant that stores the passed-in params 
    // and pushes each new product into products property (refer to constructor)
    // and adds each product to localStorage via its method setItem()

    // (A) if 'productList' exists in localStorage, append to it and exit (return)
    // (B) otherwise, use the products property of Controller

    addProduct(title, content, image){

        const storageItems = localStorage.getItem("productList");
        
        // (A)
        if (storageItems) {
            const products = JSON.parse(storageItems);
            console.log(`Testing products length ${products.length}`);
            const product = {
                id: products.length+1,  // increment the id for each newly added product
                title: title,
                content: content,
                image: image,
            }
            products.push(product);
            localStorage.setItem("productList", JSON.stringify(products));
            return;
        }
        
        // (B)
        const setId = !storageItems ? 1 : storageItems.length++;

        const product = {
            id: setId, // this.currentId++,   
            title: title,
            content: content,
            image: image,
        }
        this.products.push(product);
        localStorage.setItem("productList", JSON.stringify(this.products));
    };

    async sendJSON(title, content, image) {
        const product = {
            id: products.length+1,  // increment the id for each newly added product
            title: title,
            content: content,
            image: image,
        }
        try {
            const response = await fetch("/products", {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(product),
            });

            const result = await response.json();
            console.log("Result:", result);
        }

        catch (error) {
            console.error("Error:", error);
        }
    };
}


