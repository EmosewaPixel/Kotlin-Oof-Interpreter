# What is this?
 This is an interpreter for Oof made in Kotlin. By itself it doesn't work, as it's meant to be used as a part of other projects.

# What is Oof?
 Oof is a custom dialect of [Brainf**k](https://esolangs.org/wiki/Brainfuck), the name of which is inspired by [Ook!](https://esolangs.org/wiki/Ook!).
 
 ## Syntax
  Oof has a very simple to remember syntax:
  - `Oof` is `>` - moves the pointer to the right
  - `oOf` is `<` - moves the pointer to the left
  - `OOf` is `+` - increments the value inside the piinted cell
  - `oOF` is `-` - decrements the value inside the pointed cell
  - `OOF` is `.` - outputs the character with the ASCII code being the value the pointed cell
  - `oof` is `,` - puts an inputted character's ASCII code into the pointed cell
  - `ooF` is `[` - loops through the code between it and `OoF` unless the value of the pointed cell is 0
  - `OoF` is `]` - ends the loop
