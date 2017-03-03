## Dagger 2 explanation
Presumed that the reader got the need of dagger 2 in current android source code.
In this sample, I want to figure out dagger 2 usage, I will try to describe dagger things in easiest understanding way included image and snip code. In specific way, this project need to clarify:

- what is `module`
- what is `component`: what is `void inject`, how to expose object, get object directly from module
- what is `@scope`, `@qualifier`, `@name`, `@retention`

Here are answers:
- `module` is where we provide `object` (what we call `dependecy`, something like `new Object` we usually pass through constructor parameter), normally module contains many singleton. Some how, you can see `module` as a `singleton` container.
- `component` is exactly a **bridge** (an java `interface`), a bridge between those objects provided in `module` with where you need to use them such as: Activity, Fragment, somewhere...
  + `void inject` syntax for register: if you want inject the objects provided in `module` to your `Activity`, you need to delcare this function with argument is your Activity, i.e: `void inject(Activity activity)`
  + expose, get object: when we need this one. There are 2 cases imo:
    + If an `object **A**` in `module` also need to `inject some others object` itself, we need to `void inject(A a)`, so we can get object A by delcare a function in `component`: **`A getA()`**;
    + If we have dependency component and we want to expose some `object` in `module` for that dependecy component (remember that if we dont expose anything, the dependecy component cant use any objects provided in module).
- `@scope`: is just concept for local singleton, an vivid sample is: if your application manages many users, so we can declare a singleton just exist in an @Userscope, so everytime we login/logout all the object provided in module will be reset.But where is the start point and end point of @scope, does it define by the time we create component?
- `@qualifier`, `@name`: imagine you have 2 Context variables in same module, to avoid conflict (Dagger 2 cant resolve itself) we need to provide `@name` to identify them, i.e: `@name applicationContext`, `@name activityContext`.
- `@retention`: decide when the dependency existing (imo?), i.e: `runetime`, default is ....

Sorry for the long explanations, so in real-world how does dagger 2 work?

The sample project structure (**TBD** better with a graph to depic)

Class declaration:
- `AppModule`

