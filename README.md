# Dagger 2 explanation (MVP + Dagger2 + Retrofit + Realm + Bolts + AndroidAnnotations)
*A completion project with support actionbars, network requests, db schema and base classes.*
## 1. Introduction
Presuming that the reader got the need of dagger 2 in nowaday popular android applications.
In this sample, I want to figure out dagger 2 usage, I will try to describe dagger things in easiest understanding way included image and snip code. In specific way, this project need to clarify:

- what is `module`?
- what is `component`: what is `void inject`, how to expose object, get object directly from module?
- what is `@scope`, `@qualifier`, `@name`, `@retention`?
- What is `@inject` variable, method, constructor?
- More in [`google guide`](https://google.github.io/dagger/users-guide.html)

## 2. Explanation
Here are the answers:
- **`module`** is where we provide `object` (what we call `dependency`, something like `new Object` we usually pass through constructor parameter), normally module contains many singleton. Some how, you can see `module` as a **scope** `singleton` container.

- **`component`** is exactly a **bridge** (an java `interface`), a **bridge** between those objects provided in `module` with where you need to use them such as: Activity, Fragment, somewhere...
  + `void inject` syntax for register: if you want inject the objects provided in `module` to your `Activity`, you need to delcare this function with argument is your Activity, i.e: `void inject(Activity activity)`
  + `expose`, `get` object: when we need this one. There are 2 cases imo:
    + If an `object` **`A`** in `module` also need to `inject some others object` itself, we need to `void inject(A a)`, so we can get object A by delcare a function in `component`: **`A getA()`**. (Should check the source code for more information)
    + If we have a dependency component and we want to expose some `object` in `module` for that component (remember that if we dont expose anything, the dependecy component cant use any objects provided in module).
    
- **`@scope`**: is just concept for **local** `singleton`. 
  + For example: If we add `@scope` to our component, then we inject an `unscope` object, it will be re-created every injection times. If we inject `@scope` marked with object, it will retain same object like singleton behavior. And everything will be re-created if the component re-created.
  + `@Singleton` scope is misleading term because if your injection object is marked with `@Singleton`, it was still re-created if the component re-created, so it isn't same as in Java. We shouldn't use `@Singleton`.

- **`@qualifier`**, **`@name`**: imagine you have 2 `Context` variables in same module, to avoid conflict (Dagger 2 cant resolve itself) we need to provide `@name` to identify them, i.e: `@name applicationContext`, `@name activityContext`. The popular usage is for mock request.

- **`@retention`**: decide when the `annotation` existing policies, i.e: `runtime`... So why we need [`runtime retention`](http://stackoverflow.com/questions/36331169/why-scope-annotations-have-runtime-retention-in-dagger-2).

- **`@inject`**: to inject dependency to relevant class
  + `variable`: i.e: `@Inject App applicationContext`, inject your dependency object.
  + `method`: it works in situation we want to pass class instance itself, the params also are provided from dependency graph. Checkout the explanation [`dagger 2`](http://frogermcs.github.io/dependency-injection-with-dagger-2-the-api/)
  + `constructor`: indicate that Dagger should create instance of the class, put it in depedency graph also, and if the constructor has params, they are provided from dependency graph. Checkout the explanation [`dagger 2`](http://frogermcs.github.io/dependency-injection-with-dagger-2-the-api/)

Sorry for the long explanations, so in real-world how does dagger 2 work?

## 3. Project depiction
In brieft description: this project provide some use-cases:

- User can login/logout
- User can see the profile (uid, name, age, gender)
- User can see a report list of top-up transactions

Ofcourse:
- We will check user login/logout by RESTful API ([`Retrofit`](https://github.com/square/retrofit) + `Gson`), define by `user token`. We have another API for request transaction list, request user full info.
- We try to use same transaction list class in 2 places: in a tabview and in an activity, just try to DI by Dagger 2.
- We put everything to DB ([`Realm`](https://github.com/realm/realm-java)) to optimize performance. 

So I think its enough for depic denpendency injection by Dagger 2.

Anyway
- We apply MVP design pattern.
- We handle continuous http request by [`Bolts`](https://github.com/BoltsFramework/Bolts-Android).

## 4. Project structure
How does dagger 2 represent:  (**TBD** better with a graph demo)

Class declaration (**TBD**)
- `AppModule`: provide application context, provide database manager access, provide login session.
- `NetworkModule`: provide API client with logging interceptor.
- `LoginModule`: provide login request.
- `UserModule`: provide some requests related to user: user info, transaction list
- `---------------`
- `AppComponent`: a bridge 
- `LoginComponent`: a bridge
- `UserComponent`: a bridge
- `---------------`
- `@UserScope`: for user lifetime, after login successfully
- `@ApplicationScope`: for entire application 
- `@LoginScope`: just for login circle, sometimes it takes much operations here.

## 5. Let's start!


