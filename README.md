## Dagger 2 explanation
#### 1.
Presumed that the reader got the need of dagger 2 in current android source code.
In this sample, I want to figure out dagger 2 usage, I will try to describe dagger things in easiest understanding way included image and snip code. In specific way, this project need to clarify:

- what is `module`?
- what is `component`: what is `void inject`, how to expose object, get object directly from module?
- what is `@scope`, `@qualifier`, `@name`, `@retention`?

####2. 
Here are answers:
- **`module`** is where we provide `object` (what we call `dependency`, something like `new Object` we usually pass through constructor parameter), normally module contains many singleton. Some how, you can see `module` as a `singleton` container.

- **`component`** is exactly a **bridge** (an java `interface`), a **bridge** between those objects provided in `module` with where you need to use them such as: Activity, Fragment, somewhere...
  + `void inject` syntax for register: if you want inject the objects provided in `module` to your `Activity`, you need to delcare this function with argument is your Activity, i.e: `void inject(Activity activity)`
  + `expose`, `get` object: when we need this one. There are 2 cases imo:
    + If an `object **A**` in `module` also need to `inject some others object` itself, we need to `void inject(A a)`, so we can get object A by delcare a function in `component`: **`A getA()`**. (Should check the source code for more information)
    + If we have a dependency component and we want to expose some `object` in `module` for that component (remember that if we dont expose anything, the dependecy component cant use any objects provided in module).
    
- **`@scope`**: is just concept for **local** `singleton`, an vivid sample is: if your application manages many users, so we can declare a `singleton` which is just existing in an `@Userscope`, so everytime we login/logout all the object provided in module will be reseted. But where is the start point and end point of `@scope`, does it define by the time we create component?
- **`@qualifier`**, **`@name`**: imagine you have 2 `Context` variables in same module, to avoid conflict (Dagger 2 cant resolve itself) we need to provide `@name` to identify them, i.e: `@name applicationContext`, `@name activityContext`.

- `@retention`: decide when the dependency existing (imo?), i.e: `runetime`, default is ....

Sorry for the long explanations, so in real-world how does dagger 2 work?

#### 3.
In brieft description: this project provide some use-cases:

- User can login/logout
- User can see the profile (name, age, gender)
- User can see a report list of top-up transactions

Ofcourse:
- We will check user login/logout by RESTful API (`Retrofit + Gson`), define by `user token`. We have another API for request transactions list. 
- We put everything to DB (`Realm`) to optimize performance. So I think its enough for depic denpendency injection by Dagger 2.

#### 4.
How does dagger 2 represents:  (**TBD** better with a graph to depic)

Class declaration (**TBD**)
- `AppModule`: provide 
- `AppComponent`: a bridge 

- `NetworkModule`: provide API client: send http request for login/logout, retrieft transaction list.
- `NetworkComponent`: a bridge

- `DatabaseModule`: provide database access, sharepreferences access
- `DatabaseComponent`: a bridge

- @UserScope: for user lifetime
- @ApplicationScope: for entire application (singleton)
- @LoginScope: for login circle

#### 5. Let's start!


