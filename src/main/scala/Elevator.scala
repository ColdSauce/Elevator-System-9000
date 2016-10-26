import cats.data.Xor

/**
  * Created by campi on 10/25/2016.
  */
case class ElevatorState(currentFloor: Int, goalNumbers: Vector[Int], isGoingUp: Boolean)
case class Elevator(elevatorId: Int, elevatorState: ElevatorState) {
  private def getStepToTake: Int = {
    if (elevatorState.currentFloor == elevatorState.goalNumbers(0)) {
      0
    } else if (elevatorState.currentFloor < elevatorState.goalNumbers(0)) {
      1
    }
    else {
      -1
    }
  }
  private def checkIfIsGoingUp(stepToTake: Int): Boolean = {
    stepToTake > 0
  }

  def step: Elevator = {
    if(elevatorState.goalNumbers.length == 0) {
      this
    } else {
      val stepToTake = getStepToTake
      val goalNumbers = if(stepToTake == 0) {
        elevatorState.goalNumbers.drop(1)
      } else {
        elevatorState.goalNumbers
      }
      val isGoingUp = checkIfIsGoingUp(stepToTake)
      Elevator(elevatorId, ElevatorState(elevatorState.currentFloor + stepToTake, goalNumbers, isGoingUp))
    }
  }
}
