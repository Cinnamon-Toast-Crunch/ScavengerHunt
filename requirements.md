### Software Requirements

## Vision

**What is the vision of this product?**

Our app helps busy parents keep their kids occupied, and provides engaging “scavenger hunts” for various locations. Imagine you have to run errands with children -- our app will let you create a list of scavenger hunt objectives for the grocery store, the park, zoo, et cetera. Kids get some much needed engagement and stimulation while out in the world, and parents can have some help entertaining them.

**What pain point does this project solve?**

The app makes the ordinary exciting for kids who are homebound during COVID-19. Not only can it make a trip to the grocery store an exciting event, but it can enrich something like a visit to the park with additional active participation in a goal-oriented activity. There are limited options for kids to interact together safely; our app can add layers of enjoyment to the everyday.

**Why should we care about your product?**

Parents are perpetually involved in an escalating battle to keep kids entertained while maintaining their own sanity. Anything that provides a helping hand is valuable, especially when options are few and far between for activities to engage bored, stir-crazy kids during quarantine. We hope that our app allows parents an avenue to help kids burn off some energy so that they have even a small amount of time more to focus on their own needs personally, professionally, and for their homes and families.

## Scope (In/Out)

**IN - What will your product do.**

- Users will be able to create a profile.
- User profiles can create “scavenger hunts” -- essentially, lists of tasks for kids
- Locations can be “completed” once all tasks are checked or user moves to new location
- Tasks can be added, edited, and removed

**OUT - What will your product not do.**
- No advertisements present for kids using the app
- App will be designed for Android, and will not have a web version



## Minimum Viable Product vs Stretch Goals

**What will your MVP functionality be?**

MVP entails the ability to create a user, and for users to create a location, which contains a scavenger hunt. Scavenger hunts have lists of tasks. Tasks can be added, edited, and removed.

## Stretch

**What stretch goals are you going to aim for?**
- Alexa functionality. We’d like to be able to make our scavenger hunts educational. For instance, we’d ask a child to find a pachyderm at the zoo. They’d be able to ask Alexa what a pachyderm is, and find the rhino, thereby completing a task and learning
- Augmented reality. Treasure chests and characters would make this app more engaging for kids.
- A public locations db that contains shareable scavenger hunts. This would allow business or locations to create their own scavenger hunts in order to engage customers and visitors. This would also allow parents to share scavenger hunts that were successful
- Bar code scanner for finding and verifying items that were found in a store
- GPS boundaries for parent and child. This could also “end” a scavenger hunt when the car leaves the grocery store parking lot, e.g.
- Points system to make a game of how many tasks are completed. This could be tied to prizes a parent would award.
- Using Google Maps to trigger scavenger hunts based on GPS coordinates
- Image recognition via camera

## Functional Requirements

List the functionality of your product. This will consist of tasks such as the following:

- A user can create an account
- A user can edit their profile information
- A user can create new locations
- A user can create scavenger hunts per location, with tasks
- A child/scavenger hunt participant can access the hunts, answer questions, and check items off. They will be able to interact with AR objects if that stretch goal is met.

## Data Flow

A user (parent) will create an account. The parent will create child accounts that can participate in the scavenger hunts. They will create a location object (store, park, etc) and add a scavenger hunt to the location object. They can add tasks to the scavenger hunt. Child accounts can access scavenger hunts and check tasks off.

## Non-Functional Requirements (301 & 401 only)

- Security: our app is designed for parents and kids. Since we have a separate user type for kids, they will not see any advertisements. We’ll also hash the passwords for the parents so that there is additional security for their personal information and accounts.
- Testability: we’ll test all of our connections when setting up the scaffold for the app. We will use Espresso to test user paths.

