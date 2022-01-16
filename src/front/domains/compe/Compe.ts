import Events from "../events/Events";

export default class Compe {
  constructor(
    public compeName = '',
    public compePlace = '',
    public compeDates = '',
    public compeGuidelinesFile = null,
    public compeFeeType = '',
    public compeEvent: Events[] = []
  ) {}
}