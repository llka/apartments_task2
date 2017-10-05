import {Timestamp} from "rxjs";
export class Apartment{
  private _id: number;
  private _bookedFrom: Timestamp;
  private _bookedTo: Timestamp;
  private _cost: number;


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get bookedFrom(): Timestamp {
    return this._bookedFrom;
  }

  set bookedFrom(value: Timestamp) {
    this._bookedFrom = value;
  }

  get bookedTo(): Timestamp {
    return this._bookedTo;
  }

  set bookedTo(value: Timestamp) {
    this._bookedTo = value;
  }

  get cost(): number {
    return this._cost;
  }

  set cost(value: number) {
    this._cost = value;
  }
}
