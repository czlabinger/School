fn main() {

    teil1();
    teil2();
    teil3();

}


fn teil1() {
    println!("Teil 1");

    let mut vector: Vec<i32> = vec![];

    vector.push(-5);
    vector.push(2);
    vector.push(19);

    let mut sum: i32 = 0;

    println!("Elements in Vec: ");
    for element in vector {
        println!("{}", element);
        sum += element;
    }

    println!("Sum: {}\n", sum); 
}

fn teil2() {
   
    println!("Teil 2");

    let mut p1: Point = Point::new(5.0, 5.0);
    let p2: Point = Point::new(-5.0, -5.0);
    p1.translate(5.0, 5.0);
    println!("{}", p1.distance(&p2));
    println!("{:?}", p1.get());
    p1.set(15.0, 5.1);
    println!("{:?}", p1);
    println!("{:?}", p2);
    println!("{}\n", p2.abs());
    

}


fn teil3() {

    println!("Teil 3");

    let r1: RockPaperScissors = RockPaperScissors::Rock;
    let r2: RockPaperScissors = RockPaperScissors::Paper;
    let r3: RockPaperScissors = RockPaperScissors::Scissors;
    let mut result: Result = r1.play(&r2);
    r2.play(&r3);
    println!("{:?}", result);
    println!("{:?}", result.swap())
}

//// This struct represents a point with a x and y coordinate
#[derive(Debug)]
struct Point {
    x: f64,
    y: f64
}

//// Implementation of the point struct
impl Point {

    //// Constructor
    fn new(x: f64, y: f64) -> Self {
        Self {x, y}
    }

    //// Get x and y of a point
    fn get(&self) -> (f64, f64) {
        (self.x, self.y)
    }

    //// Set x and y of a point
    fn set(&mut self, x: f64, y: f64) {
        self.x = x;
        self.y = y;
    }

    //// Offset x and y of a point by x_offset and y_offset
    fn translate(&mut self, x_offset: f64, y_offset: f64){ 
        self.x += x_offset;
        self.y += y_offset;
    }

    //// Get the distance between two points
    fn distance(&self, p: &Point) -> f64 {
        let x_distance = self.x - p.x;
        let y_distance = self.y - p.y;
        (x_distance * x_distance + y_distance * y_distance).sqrt()
    }

    //// Get the distance of a point to (0/0)
    fn abs(&self) -> f64 {
        self.distance(&Point::new(0.0, 0.0))
    }

}


//// Represents the choises of a Rock Paper Scissors game
#[derive(Debug)]
enum RockPaperScissors {
    Rock,
    Paper,
    Scissors
}

//// Implementation of Rock Paper Scissors game
impl RockPaperScissors {

    //// Play a game of Rock Paper Scissors
    fn play(&self, other: &RockPaperScissors) -> Result {

        match (self, other) {
            (RockPaperScissors::Rock, RockPaperScissors::Rock) => Result::Draw,
            (RockPaperScissors::Rock, RockPaperScissors::Paper) => Result::Lose,
            (RockPaperScissors::Rock, RockPaperScissors::Scissors) => Result::Win,
            (RockPaperScissors::Paper, RockPaperScissors::Rock) => Result::Win,
            (RockPaperScissors::Paper, RockPaperScissors::Paper) => Result::Draw,
            (RockPaperScissors::Paper, RockPaperScissors::Scissors) => Result::Lose,
            (RockPaperScissors::Scissors, RockPaperScissors::Rock) => Result::Lose,
            (RockPaperScissors::Scissors, RockPaperScissors::Paper) => Result::Win,
            (RockPaperScissors::Scissors, RockPaperScissors::Scissors) => Result::Draw,
        }
    }
}

//// Outcomes of a Rock Paper Scissors game
#[derive(Debug)]
enum Result {
    Win,
    Lose,
    Draw
}

//// Implementation of the Result enum
impl Result {

    //// Swap Win <-> Lose
    fn swap(&mut self) -> Result {
        match self {
            Result::Win => Result::Lose,
            Result::Lose => Result::Win,
            Result::Draw => Result::Draw,
        }
    }
}
