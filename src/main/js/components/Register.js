import React from 'react';
import ReactDOM from 'react-dom';
import {Form,FormGroup,ControlLabel,FormControl,Button,Tooltip,Overlay} from 'react-bootstrap';

export default class LoginForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {usernameError:{message:"", exist:false},
            passwordError:{message:"", exist:false},
            passwordConfirmError:{message:"", exist:false},
            emailError:{message:"", exist:false}}
    }


    register(){
        const username=this.username.value;
        const password=this.password.value;
        const passwordConfirm=this.passwordConfirm.value;
        const email=this.email.value;
        if(this.validate(username,password,passwordConfirm,email)){
        $.ajax({
            url: '/registration',
            type: 'POST',
            contentType: "application/json",
            dataType: 'json',
            success: (function(data) {
                console.log(data);
            }).bind(this),
            error: (function (xhr, ajaxOptions, thrownError) {
                var errors=JSON.parse(xhr.responseText);
                this.setState({usernameError: this.mapping("username",errors.fieldErrors)});
                this.setState({passwordError: this.mapping("password",errors.fieldErrors)});
                this.setState({passwordConfirmError: this.mapping("passwordConfirm",errors.fieldErrors)});
                this.setState({emailError: this.mapping("email",errors.fieldErrors)});
            }).bind(this),
            data:JSON.stringify({
                "username":username,
                "password":password,
                "passwordConfirm":passwordConfirm,
                "email":email
            })
        });}
    }

    validate(username,password,passwordConfirm,email){
        var result=true;
        if(username.length<6 || username.length>32){
            result=false;
            this.setState({usernameError: {
                message:"Please use count of characters between 6 and 32.",
                exist:true}
            });
        }

        if(password.length<8 || password.length>32){
            result=false;
            this.setState({passwordError: {
                message:"Password should have more than 8 characters.",
                exist:true}});
        }

        if(password!==passwordConfirm){
            result=false;
            this.setState({passwordConfirmError: {
                message:"Confirmation of password does not match to password.",
                exist:true}});
        }
        if(!/^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/.test(email)){
            result=false;
            this.setState({emailError: {
                message:"Email don't have appropriate form.",
                exist:true}});
        }

        return result;
    }

    mapping(fieldName,table) {
        var i;
        for (i = 0; i < table.length; i++) {
            if(table[i].field==fieldName){
                return {
                    message:table[i].message,
                    exist:true
                }}
        }
        return{
            message:"",
            exist:false
        }

    }

    render(){

        return(
            <Form>
                <FormGroup bsSize="large">
                    <h4><ControlLabel>User name:</ControlLabel></h4>
                    <FormControl type="text" id="username" inputRef={(ref) => {this.username = ref}} placeholder="Jarek512"/>
                    <Overlay onHide={() => this.setState({ usernameError:{message:"", exist:false }})} rootClose={true} show={this.state.usernameError.exist} target={ () => ReactDOM.findDOMNode(this.username)} container={this} placement="top">
                        <Tooltip id="usernameError">{this.state.usernameError.message}</Tooltip>
                    </Overlay>
                </FormGroup>
                <FormGroup bsSize="large">
                    <h4><ControlLabel>Email address:</ControlLabel></h4>
                    <FormControl type="email" id="email" placeholder="jarek512@gmail.com" inputRef={(ref) => {this.email = ref}}/>
                    <Overlay onHide={() => this.setState({ emailError:{message:"", exist:false }})} rootClose={true} show={this.state.emailError.exist} target={ () => ReactDOM.findDOMNode(this.email)} container={this} placement="top">
                        <Tooltip positionTop={100} id="emailError">{this.state.emailError.message}</Tooltip>
                    </Overlay>
                </FormGroup>
                <FormGroup bsSize="large">
                    <h4><ControlLabel>Password:</ControlLabel></h4>
                    <FormControl type="password" id="password" inputRef={(ref) => {this.password = ref}}/>
                    <Overlay onHide={() => this.setState({ passwordError:{message:"", exist:false }})} rootClose={true} show={this.state.passwordError.exist} target={ () => ReactDOM.findDOMNode(this.password)} container={this} placement="top">
                        <Tooltip positionTop={100} id="passwordError">{this.state.passwordError.message}</Tooltip>
                    </Overlay>
                </FormGroup>
                <FormGroup bsSize="large">
                    <h4><ControlLabel>Confirm password:</ControlLabel></h4>
                    <FormControl type="password" id="passwordConfirm" inputRef={(ref) => {this.passwordConfirm = ref}}/>
                    <Overlay onHide={() => this.setState({ passwordConfirmError:{message:"", exist:false }})} rootClose={true} show={this.state.passwordConfirmError.exist} target={() => ReactDOM.findDOMNode(this.passwordConfirm)} container={this} placement="top">
                        <Tooltip positionTop={100} id="passwordConfirmError">{this.state.passwordConfirmError.message}</Tooltip>
                    </Overlay>
                </FormGroup>
                <Button bsStyle="primary" bsSize="large" onClick={this.register.bind(this)} block>
                    Register
                </Button>
            </Form>
        );
    }
}