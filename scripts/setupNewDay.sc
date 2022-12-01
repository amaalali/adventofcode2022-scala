#!/usr/bin/env -S scala-cli shebang

//> using lib "com.lihaoyi::os-lib:0.8.1"

import scala.util.matching.Regex.Match
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

/** PART 1: Validate Input
  */
val validDayFormat = """^(0[1-9]|1\d|2[0-5])$""".r

val validDayErrorMessage =
  "Day must be between 01 and 25 inclusive with leading 0."

val (dayAsString, dayAsInt): (String, Int) =
  args.toList.headOption
    .toRight(
      new IllegalArgumentException(
        "Program needs a day number. " + validDayErrorMessage
      )
    )
    .map(validDayFormat.findFirstMatchIn)
    .flatMap(
      _.toRight(
        new IllegalArgumentException(
          "Invalid input for day. " + validDayErrorMessage
        )
      )
    )
    .fold(
      err => throw err,
      value => ("day" + value.matched, value.matched.toInt)
    )

/** PART 2: Validate project with input value
  */
val projectRootDir = os.pwd / os.up

// Check if there is a directory with name
val exisitingDirectories = os.list(projectRootDir).toSeq.map(_.last)

if (exisitingDirectories.contains(dayAsString)) {
  throw new IllegalArgumentException(
    s"Invalid name provided. There is already a folder/file with name `${dayAsString}`"
  )
}

// validate that there is a template folder
val templateDirName = "dayXX_template"
val templateSourceDir = projectRootDir / templateDirName
if (!os.list(projectRootDir).contains(templateSourceDir)) {
  throw new RuntimeException(
    s"Could not find the template directory `${templateDirName}`. The working directory must be the same as script and template"
  )
}

/** PART 3: Cleanup
  */
// remove scala-cli folders and files from template folder if they exist
os.remove.all(templateSourceDir / ".scala")
os.remove.all(templateSourceDir / ".bsp")

/** PART 4: Do work
  */
// Create directories
val destinationDir = projectRootDir / dayAsString
os.makeDir(destinationDir)

// Copy over files
for (sourceFilePath <- os.list.stream(templateSourceDir)) {
  os.copy.into(sourceFilePath, destinationDir)
}

println(s"New folder for ${dayAsString} created from template! Happy coding :)")
