library(shiny)

ui <- fluidPage(
  
  titlePanel("Hello Shiny!"),
  sidebarLayout(
    sidebarPanel(
      sliderInput(inputId = "bins",
                  label = "Number of bins:",
                  min = 1,
                  max = 50,
                  value = 30)
      
    ),
    
    mainPanel(
      plotOutput(outputId = "distPlot")
    )
  )
)

server <- function(input, output) {
  
  output$distPlot <- renderPlot({
    
    x    <- state.x77[,"Population"]
    bins <- seq(min(x), max(x), length.out = input$bins + 1)
    
    hist(x, breaks = bins, col = "#007bc2", border = "white",
         xlab = "Population",
         main = "Histogram of Population of the States in the US",
         ylab = "Sates with x Population")
  })
}

shinyApp(ui = ui, server = server)