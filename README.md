# keystone

Material for KeystoneDH 2017.

## Using material in the `scripting` directory

Start up an sbt console as follows:

1.  In a bash shell, `cd` into the `scripting` directory
2.  `sbt`
3.  Explicitly set the version: `++ 2.11.8`
4.  `console`
5.  `:load pitch.sc`

This loads all necessary libraries, and defines two functions:  `computePitch` and `printPitch`.


### computePitch

`computePitch` requires a `Corpus` parameter for the text you want to analyze.  (Remember that you can create a `Corpus` with a `CorpusSource`, e.g., `val corpus = CorpusSource.fromFile("FILENAME")`).  It returns a Vector of Strings, where each string represents the accent pattern for one line, displaying `/`, `\`, `=` or `_` for acute, grave, circumflex, or no accent on the syllable.

Example of what you would see in your console:


    val corpus = CorpusSource.fromFile("iliad-oct.tsv")
    val pitches = computePitch(corpus)
    Converting text corpus to Greek Strings ...
    Syllabifying...
    Formatting text...
    pitches: Vector[String] = Vector(=_/___\___/___ ...


### printPitch

`printPitch` successively prints syllables and accent patterns for a given passage of text.  It requires a `CtsUrn` identifying the passage, a `Corpus` to select texts from, and a Vector of Strings with pitch patterns.

Example session in sbt console:

    val corpus = CorpusSource.fromFile("iliad-oct.tsv")
    val pitches = computePitch(corpus)

    val passage = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:1.1-1.5")
    printPitch(passage,corpus,pitches)

    μῆ-νιν-ἄ-ει-δε-θε-ὰ-Πη-λη-ϊ-ά-δε-ω-#Α-χι-λῆ-οσ
    =_/___\___/____=_

    οὐ-λο-μέ-νην,-ἣ-μυ-ρί'-#Α-χαι-οῖσ-ἄλγε'-ἔ-θη-κε,
    __/_\_/__=//__

    πολ-λὰσ-δ'-ἰ-φθί-μουσ-ψυ-χὰσ-##Α-ϊ-δι-προί#α-ψεν
    _\__/__\___/_

    ἡ-ρώ-ων,-αὐ-τοὺσ-δὲ-ἑ-λώ-ρι-α-τεῦ-χε-κύ-νεσ-σιν
    _/__\\_/__=_/__

    οἰ-ω-νοῖ-σί-τε-πᾶ-σι,-Δι-ὸσ-δ'-ἐ-τε-λεί-ε-το-βου-λή,
    __=/_=__\___/___/
