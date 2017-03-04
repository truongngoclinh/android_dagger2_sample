# Dagger 2 explanation (MVP + Retrofit + Realm + Bolts)
## 1. Introduction
Presumed that the reader got the need of dagger 2 in nowaday popular android applications.
In this sample, I want to figure out dagger 2 usage, I will try to describe dagger things in easiest understanding way included image and snip code. In specific way, this project need to clarify:

- what is `module`?
- what is `component`: what is `void inject`, how to expose object, get object directly from module?
- what is `@scope`, `@qualifier`, `@name`, `@retention`?
- What is `@inject` variable, method, constructor?
- More in [`google guide`] (https://google.github.io/dagger/users-guide.html)

## 2. Explanation
Here are answers:
- **`module`** is where we provide `object` (what we call `dependency`, something like `new Object` we usually pass through constructor parameter), normally module contains many singleton. Some how, you can see `module` as a **scope** `singleton` container.

- **`component`** is exactly a **bridge** (an java `interface`), a **bridge** between those objects provided in `module` with where you need to use them such as: Activity, Fragment, somewhere...
  + `void inject` syntax for register: if you want inject the objects provided in `module` to your `Activity`, you need to delcare this function with argument is your Activity, i.e: `void inject(Activity activity)`
  + `expose`, `get` object: when we need this one. There are 2 cases imo:
    + If an `object` **`A`** in `module` also need to `inject some others object` itself, we need to `void inject(A a)`, so we can get object A by delcare a function in `component`: **`A getA()`**. (Should check the source code for more information)
    + If we have a dependency component and we want to expose some `object` in `module` for that component (remember that if we dont expose anything, the dependecy component cant use any objects provided in module).
    
- **`@scope`**: is just concept for **local** `singleton`, a vivid sample is: if your application manages many users, so we can declare a `singleton` which is just existing in an `@Userscope`, so everytime we login/logout all the object provided in module will be re-created. But where is the start point and end point of `@scope`, does it define by the time we create component?
- **`@qualifier`**, **`@name`**: imagine you have 2 `Context` variables in same module, to avoid conflict (Dagger 2 cant resolve itself) we need to provide `@name` to identify them, i.e: `@name applicationContext`, `@name activityContext`.

- **`@retention`**: decide when the `annotation` existing policies, i.e: `runtime`, default is `class`: mean annotation avaiable in the source and class file. So why we need [`runtime retention`] (http://stackoverflow.com/questions/36331169/why-scope-annotations-have-runtime-retention-in-dagger-2), I'm actually not sure :)

- **`@inject`**: to inject dependency to relevant class
  + `variable`: i.e: `@Inject App applicationContext`, inject your dependency object.
  + `method`: it works in situation we want to pass class instance itself, the params also are provided from dependency graph. Checkout the explanation `[dagger]` (http://frogermcs.github.io/dependency-injection-with-dagger-2-the-api/)
  + `constructor`: indicate that Dagger should create instance of the class, put it in depedency graph also, and if the constructor has params, they are provided from dependency graph. Checkout the explanation `[dagger 2]` (http://frogermcs.github.io/dependency-injection-with-dagger-2-the-api/)

Sorry for the long explanations, so in real-world how does dagger 2 work?

## 3. Project depiction
In brieft description: this project provide some use-cases:

- User can login/logout
- User can see the profile (uid, name, age, gender)
- User can see a report list of top-up transactions

Ofcourse:
- We will check user login/logout by RESTful API ([`Retrofit`] (https://github.com/square/retrofit) + `Gson`), define by `user token`. We have another API for request transactions list. 
- We put everything to DB ([`Realm`] (https://github.com/realm/realm-java)) to optimize performance. So I think its enough for depic denpendency injection by Dagger
2.
- We apply MVP design pattern.
- We handle continuous http request by [`Bolts`] (https://github.com/BoltsFramework/Bolts-Android)

## 4. Project structure
How does dagger 2 represents:  (**TBD** better with a graph to depic)

Class declaration (**TBD**)
- `AppModule`: provide 
- `AppComponent`: a bridge 
- `---------------`
- `NetworkModule`: provide API client: send http request for login/logout, retrieve transaction list.
- `---------------`
- `DatabaseModule`: provide database access, sharepreferences access
- `---------------`
- `LoginComponent`:
- `---------------`
- `@UserScope`: for user lifetime
- `@ApplicationScope`: for entire application (singleton)
- `@LoginScope`: for login circle

## 5. Let's start!


