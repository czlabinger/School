#import "../lib/variables.typ": *
#pagebreak()

#set page(paper: "a4", numbering: "1 von 1", number-align: right, footer: "Christof Zlabinger")

#set heading(numbering: "1.")
= Finetuning & Execution of the LLM
<llm>

One of the main components of the #projectname application is the LLM that determines the probability of a stroke given the input of the user. This section is about researching the most popular and top-of-the-notch technologies, evaluating their pros and cons and deciding which of these technologies fits best for this individual purpose.

== Hauptteil

=== LLMs

Locally referes to running on a Nvidia GForce 3050 Mobile and Intel Core i5-11400H.

#set table(
  stroke: none,
  gutter: 0.2em,
  fill: (x, y) => {
    if calc.rem(y, 2) == 1 { rgb("#eee") }
    if calc.rem(y, 2) == 0 { rgb("#FC7788") }},
  inset: (right: 0.8em),
)

#show table.cell: it => {
  if calc.rem(it.y, 2) == 0 {
    if it.x == 0 or it.y == 0 {
      strong(it)
    }else {
      it
    }
  }else {
    if it.x == 0 or it.y == 0 {
      strong(it)
    }else {
      it
    }
  }
}

#set align(center)
#table(
  columns: 4,
  [Name], [Size], [Response time], [Coherency],

  [#link("https://huggingface.co/mattshumer/Reflection-Llama-3.1-70B")[Reflection-Llama-3.10-70B]], [\~280GB], [\~260s], [TODO: Response bekomen],
  [#link("https://huggingface.co/vicgalle/gpt2-open-instruct-v1")[gpt2-open-instruct-v1]], [\~0.5GB], [\~10s], [Random words as response],
  [#link("https://huggingface.co/akjindal53244/Llama-3.1-Storm-8B")[Llama-3.1-Storm-8B]], [\~16GB], [\~60s], [Clear response],
)

#set align(left)

==== Reflection-Llama-3.1-70B

Reflection-Llama-3.1-70B is a model with 70 Billion parameters created for complex natural language processing. It is used for tasks such as summarization, text generation, question answering and tasks that require reasoning. @reflection-llama-3.1-70B

==== gpt2-open-instruct-v1

==== Llama-3.1-Storm-8B


=== Languages

#set table(
  stroke: none,
  gutter: 0.2em,
  fill: (x, y) => {
    if calc.rem(y, 2) == 1 { rgb("#eee") }
    if calc.rem(y, 2) == 0 { rgb("#FC7788") }},
  inset: (right: 0.8em),
)

#show table.cell: it => {
  if calc.rem(it.y, 2) == 0 {
    if it.x == 0 or it.y == 0 {
      strong(it)
    }else {
      it
    }
  }else {
    if it.x == 0 or it.y == 0 {
      strong(it)
    }else {
      it
    }
  }
}

#set align(center)
#table(
  columns: 4,
  [Name], [Experience], [Performance], [Support for LLMs],

  [Python], [], [], [Most modules for LLMs],
  [Julia], [], [], [Some Modules for LLMs],
  [R], [], [], [A few Libraries for LLMs],
)

#set align(left)

==== Python
<Python>

#link("https://huggingface.co/docs/transformers/en/index")[Hugging Face Transformers]
#link("https://pytorch.org/")[PyTorch]
#link("https://www.tensorflow.org/")[TensorFlow]

- Experience with the language and modules
- Most modules for LLMs

==== Julia

#link("https://github.com/JuliaAI/MLJ.jl")[MLJ.jl]

- No experience with language
- High performance

==== R

#link("https://tensorflow.rstudio.com/")[tensorflow for R]
#link("https://tensorflow.rstudio.com/guides/keras/basics")[keras for R]

- Some experience with the language but no experience with the libraries
- Less mature than @Python[Python]

=== Hosting for Finetuning

Most LLMs are too large to train locally which is why a server with enough VRAM is needed. Possible options are:

==== Google Colab
- Very fast GPUs including NVIDIA T4
- 10 Euros/Month

==== TGM
- //TODO: What?

==== Huggingface
- Wide variety of GPUs 
- Different prices also including Nvidia T4 - Medium for \$0.60/hour

#bibliography("../bib.bib")