# αlphaБetter

## Overview
  Alphabetter is a alphabet learning app for andriod offering Russian, Greek, and Armenian. Alphabeter offers two main modes, learning mode, and quiz mode, with varying levels of difficulty. Written in Java using Android Studio. 

<a href="http://www.youtube.com/watch?feature=player_embedded&v=YOUTUBE_VIDEO_ID_HERE
" target="_blank"><img src="http://img.youtube.com/vi/YOUTUBE_VIDEO_ID_HERE/0.jpg"/></a>

 By: Yana Galina, Vanessa Schuweh, Kenwood Harris, Tariq Ahsan, Elizabeth Slade
 
## How to Run
To run this program, download the zip or clone/fork this repository. Then open the requisite folder (unzipped if downloaded as a zip file) in Android Studio.

## Activities
  AlphaBetter consists of five main Activity Screens (*Main Activity, Learn Activity, Difficulty Activity, Quiz Activity and End Screen Activity*) which follow this general diagram:
  
  
  
 ![AlphaBetterDiagram](https://github.com/khjharris/EC327_Project/blob/master/documentation/AlphaBetterDiagram.png)
 
 
 #### Main Activity
 The Main activity lists the first two modes, learn mode and quiz mode, and lists the three different language options Russian, Greek, and Armenian. 
 
#### Learn Activity
The learn activity allows the user to learn the alphabet, by listening to the pronunciation of the letter by a native speaker, and/or tracing the character, all at their own pace. Utilizes the MediaPlayer, and Canvas Classes.

#### Quiz Activity
The quiz activity allows the user to practice the knowledge they obtained from the learn section or elsewhere to prove their knowledge with a timed quiz. The user is allowed to choose difficulty(Using the Difficulty Activity), then the quiz begins. The quiz is avaliable for all the langugages.

#### Difficulty Activity
Once the user selects the quiz that he/she wishes, the difficulty must be selected. The Difficulty Activity allows for two choices, Regular or Hard. This is then picked up by an intent in the Quiz Activity.

#### End Screen Activity
The end screen Activity reports to the user the result of their Quiz or Learning Activity. At the end of the quiz the score is reported, and Win/Loss message is given, while at the end of the learning activity the user is greeted with a success in learning the respective alphabet.

