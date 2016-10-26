import cats.data.Xor

/**
  * Created by campi on 10/25/2016.
  */
object Launcher {
  /**
    * Main function that just tests.
    * @param args
    */
  def main(args: Array[String]): Unit = {
    val elevators = Vector(Elevator(1, ElevatorState(1, Vector(14), false)), Elevator(2, ElevatorState(1, Vector(7), false)))
    val listeners = Vector[(Elevator) => Unit](testListenerFunction)
    val possibleElevatorSystem = ElevatorSystem.createFromElevators(elevators, listeners)
    val elevatorSystem  = possibleElevatorSystem.map {
      elevatorSystem =>
        elevatorSystem.step.step.step.step.step.step.step.step.step.step
    } match {
      case Xor.Right(someElevatorSystem) => someElevatorSystem.getAllStatus.foreach(println)
      case Xor.Left(someError) =>
        println(s"Could not create an elevator system. Reason: $someError")
        return
    }
  }

  def testListenerFunction(elevator: Elevator): Unit = {
    println(s"elevator ${elevator.elevatorId} is now on the ${elevator.elevatorState.currentFloor} and goal is to get to ${elevator.elevatorState.goalNumbers}")
  }
}
