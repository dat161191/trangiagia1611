import {FacilityType} from './FacilityType';
import {RentType} from './RentType';

export interface Facility {
  id?: number;
  facilityCode?: string;
  name?: string;
  area?: number;
  cost?: number;
  maxPeople?: number;
  facilityType?: FacilityType;
  rentType?: RentType;
  standardRoom?: string;
  descriptionOtherConvenience?: string;
  poolArea?: number;
  numberOfFloors?: number;
  facilityFree?: string;
}
