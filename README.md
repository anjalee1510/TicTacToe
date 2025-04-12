Tic Tac Toe – Java Console Game
A command-line implementation of the classic Tic Tac Toe game in Java. This project features:

Player vs. Player and Player vs. AI modes.

AI Difficulty Levels: Easy, Medium, and Hard.

Modular Design: Utilizes object-oriented principles for scalability and maintainability.

🧠 Features
Interactive Gameplay: Engaging CLI-based interface.

AI Integration: The Hard AI leverages OpenAI's GPT-3.5-turbo model for intelligent move suggestions.

Clean Architecture: Organized codebase with clear separation of concerns.

🗂️ Project Structure

models/: Contains classes representing the game's data structures (e.g., Board, Cell, Player).

strategy/: Implements different playing strategies, including AI logic.

controllers/: Manages game flow and user interactions.

Main.java: Entry point of the application.

🚀 Getting Started
Prerequisites
Java Development Kit (JDK): Version 11 or higher.

Maven: For dependency management and build automation.

🎮 How to Play
Start the Game: Upon running, the application prompts for game mode selection.

Make Moves: Players input their moves by specifying row and column numbers.

AI Interaction: In AI modes, the computer makes moves based on the selected difficulty.

🤖 AI Strategy
Easy & Medium: Utilize predefined heuristics for move selection.

Hard: Integrates with OpenAI's GPT-3.5-turbo model to determine optimal moves based on the current board state.

🛠️ Technologies Used
Java: Core programming language.

Maven: Build and dependency management.

OpenAI API: For advanced AI move suggestions in Hard mode.

