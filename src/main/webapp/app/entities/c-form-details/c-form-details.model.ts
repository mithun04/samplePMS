import { BaseEntity } from './../../shared';

export class CFormDetails implements BaseEntity {
    constructor(
        public id?: number,
        public dateOfBirth?: any,
        public placeOfBirth?: string,
        public arrivedFrom?: string,
        public departingTo?: string,
        public passportNo?: string,
        public passportExpirationDate?: any,
        public employedinIndia?: boolean,
        public passportIssuedAuthority?: string,
        public dateOfArrivalInIndia?: any,
        public placeOfIssue?: string,
        public visaType?: string,
        public visaNo?: string,
        public visaEffectiveDate?: any,
        public visaExpiryDate?: any,
        public visaIssuedAuthorityName?: string,
    ) {
        this.employedinIndia = false;
    }
}
