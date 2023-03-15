import {Trademark} from './trademark';
import {MachineType} from './machine-type';

export interface ClockDetail {
  id?: number;
  price?: number;
  quanlity?: number;
  origin?: string;
  faceDiameter?: string;
  thick?: string;
  shellMaterial?: string;
  ropeMaterial?: string;
  glassMaterial?: string;
  resistanceWater?: string;
  note?: string;
  name?: string;
  countryRegistration?: string;
  gender?: boolean;
  trademark?: Trademark;
  machineType?: MachineType;
}
