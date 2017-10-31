import { BaseEntity } from './../../shared';
import {Profile} from "./profile.model";

export class Customer implements BaseEntity {
    constructor(
        public id?: number,
        public crmContactId?: string,
        public leadId?: string,
        public gstNumber?: string,
        public profile?: Profile,
    ) {
        this.profile = {};
    }
}
