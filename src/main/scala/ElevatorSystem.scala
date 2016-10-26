import ElevatorSystem.ElevatorMalfunctionError

/**
  * Created by campi on 10/25/2016.
  */
class ElevatorSystem(elevators: Vector[Elevator], listeners: Vector[(Elevator, Int, Int) => Either[ElevatorMalfunctionError, Unit]]) {


  def getAllStatus: Vector[(Elevator, Int, Int)] = {
    elevators.map(elevator => (elevator, elevator.elevatorState.currentFloor, elevator.elevatorState.goalNumber))
  }

  def createPickup(floorNumber: Int): Elevator = {
    val elevatorToUse = elevators.reduce[Elevator]((acc, curr) => {
      Util.nearer(acc, curr, floorNumber)
    })

    Elevator(ElevatorState(elevatorToUse.elevatorState.currentFloor, floorNumber))
  }


  def update(elevator: Elevator, currentFloor: Int, goalNumber: Int): Unit = {// Either[ElevatorMalfunctionError, Unit] = {
    listeners.foreach(_(elevator, currentFloor, goalNumber))
//    listeners.reduce[(Elevator, Int, Int) => Either[ElevatorMalfunctionError, Unit]]((acc, curr) => {
//      for {
//        acc <- acc
//        curr <- curr
//      } yield {
//        (curr(elevator, currentFloor, goalNumber))
//      }
//    }
  }






}

object ElevatorSystem {
  trait ElevatorMalfunctionError
  case object OutOfFuelError
  def createFromElevators(elevators: Vector[Elevator], listeners: Vector[(Elevator, Int, Int) => Either[ElevatorMalfunctionError, Unit]]): ElevatorSystem = {
    return new ElevatorSystem(elevators, listeners)
  }
}
