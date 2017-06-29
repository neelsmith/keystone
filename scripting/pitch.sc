
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.greek._
import edu.holycross.shot.gsphone._

// Example of how to make a corpus from a file:
//val corpus = CorpusSource.fromFile(fileName)

// Example of a CtsUrn identifying a passage in the OCT Iliad
///val passage = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.omar:1.1-1.7")


/** Create a vector of strings for each passage in a corpus of texts,
* where the string represents syllables as acute '/', grave '\', circumflex
* '=' or no accent '_'.
*
* @param corpus The corpus of texts to work on.
*/
def computePitch(corpus: Corpus): Vector[String] = {
  val wordVect = corpus.nodes.map(cn => cn.text.split(" "))


  println("Converting text corpus to Greek Strings ...")
  val greekWords = wordVect.map(v => v.map { LiteraryGreekString(_) } )
  val gwVects = greekWords.map( _.toVector)
  println("Syllabifying...")
  val syllableVects = gwVects.map(LGSyllable.syllabify(_))
  println("Formatting text...")
  val pitchPatterns= syllableVects.map(v => v.map(_.accent.getOrElse("_")).mkString)

  pitchPatterns
}

/** For a requested passage, successively print syllables and accent
* pattern.
*
* @param u URN identifying passage to print.
* @param corpus Corpus of texts.
* @param pitches Vector of strings with pitch patterns.
*/
def printPitch(u: CtsUrn, corpus: Corpus, pitches: Vector[String]) {
  val urns = corpus.nodes.map(_.urn)
  val selected = corpus ~~ u
  for (cn <- selected.nodes) {
    val count = urns.indexOf(cn.urn)
    val words = cn.text.split(" ").toVector
    val greekWords = words.map(LiteraryGreekString(_))
    val syllables = LGSyllable.syllabify(greekWords)
    println(syllables.map(_.ucode).mkString("-"))
    println(pitches(count).mkString + "\n")
  }
}
