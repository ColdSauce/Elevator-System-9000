/**
  * Created by campi on 10/25/2016.
  */
class ElevatorSystem(elevators: Vector[Elevator]) {

  def getAllStatus: Vector[(Elevator, Int, Int)] = {
    elevators.map(elevator => (elevator, elevator.status._1, elevator.status._2))
  }

  def getStatus(elevator: Elevator): (Int, Int) = {
    elevator.status
  }


}

object ElevatorSystem {
  def createFromElevators(elevators: Vector[Elevator]): ElevatorSystem = {
    return new ElevatorSystem(elevators)
  }
}
