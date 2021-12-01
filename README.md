# Project Notes: RasPiRetail

- Follows the MVC (Model, View, Controller) Architecture.
- As apart from the MVC Architecture, the separation of the frontend code from the backend is done to modularise the process.
- Uses the Command Line Interface (CLI) in place of Java Servlets and JSP.


## Design Patterns currently used
- **Factory**
- **Abstract Factory**
- **Prototype**
- **Singleton**
- **Facade**
- **Builder**
- **Iterator**

## How are they Implemented?

### [Factory, Abstract Factory]
- Factory and Abstract Factory can be found inside the `backend.model`
- There are two factories: `DAOFactory` and `DTOFactory`
- `DAOFactory` deals with making DAOs, or Data Access Objects, which handles the SQL queries to and from the database.
- `DTOFactory` deals with making DTOs, or Data Transfer Objects, which makes up the schema of the data based from the database to the program.

### [Prototype]
- Prototyping is found from each of the factories, where the factory produces a prototype of what type of DAO/DTO it is requesting.

### [Singleton]
- Singleton is implemented for the Database Driver `DBDriverService.java` located in `backend.util`.
- Singleton is also implemented for all DAOs respectively, as these DAOs must handle requests and responses to and from the app to and from the database.
- The `DAOFactory` creates a singleton instance for each respective DAO.

### [Facade]
- The `Facade` interface is located at the `frontend.controller.middleware`, as the `middleware` package handles all the frontend requests to the backend code that serves the data from the database.
- The `Facade`'s children are the main controllers that work with the backend to request and send data to and from the database.

### [Builder]
- The `Builder` design pattern is implemented through the PackageDTO, where it has a `Builder` class that is used to set its values upon packing, and saving it into the database through the respected DAO.

### [Iterator]
- The `Iterator` design pattern is implemented through the DAO Interface, which makes all its children use Iterators over ArrayLists for passing data through other classes of the program.

> Separating the frontend and backend is something we decided in order to organise how the program will function as a whole. This also simulates a practice done in Web Development in which, an entire stack is separated into at most, 3 parts.
> This includes:
> > **Frontend**: Mostly the `view` part of the MVC, as this is the one exposed to users to use the program provided.
> 
> > **Backend**: Mostly the `controller` and `model` part of the MVC, as this handles the logic and managing the data that will be given to the frontend to show the data, or to mutate/update the data requested by the frontend.
> 
> > **Database**: Contains the data that can only be accessed through the backend, as this is where the backend stores and manages their data located on a database program.

## How does the program work on the frontend?

> There are three types of users for this program.
> - Guest
> - Customer/Member
> - Admin

### The Guest
- Guest users can choose to do the ff:
  - Window Shop for Products (View only products)
  - Sign up to be a member
  - Do a one-time purchase (only purchase from the guest's cart once)

> REMARKS: 
> - A one-time purchase would have the same functionalities as the customer/member, but the only difference is that when the guest checks out and has successfully recieved the item, their userdata will be disposed of, unlike if they were to be a member.
> - We assume that the end of a guest user's session upon checkout is that the guest user is able to recieve the product/s from their cart upon terminating the program.

### The Customer/Member
- Customer/Member users can choose to do the ff:
  - Window Shop for products (view only products)
  - Manage their cart (add items to cart, update items to cart, clear items to cart)

### The Admin
- Admin users can choose to do the ff:
  - Manage Products in shop (add products, update product details and/or inventory stock, remove products)

## How does the program work on the backend?

- Each respective frontend methods have their own respective backend methods.
- For example, if a customer logins and does their "shopping" from within the program, the only controllers that function in the backend are anything relating to that customer.
- Each SQL Table in the database has their corresponding DAO+DTO pair, and the DAO (Data Access Object) would be able to handle requests to and from the frontend through the `middleware` package. While the DTO (Data Transfer Object) is used to form the schema of the data from the table and convert it as classes inside the program.
- The `middleware` package is the only one exposed to the backend, and executes database tasks to manipulate the database data. This is located in the frontend to ensure that anything done in the backend would only focus on handling the database.