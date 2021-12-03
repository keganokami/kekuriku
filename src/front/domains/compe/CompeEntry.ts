import EntryEvents from "../events/EntryEvents";

export default class Compe {
  constructor(
    public compeName = '',
    public compePlace = '',
    public compeDates = '',
    public compeGuidelinesFile = null,
    public compeEvent: EntryEvents[] = []
  ) { }
}