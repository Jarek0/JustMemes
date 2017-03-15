import React from 'react';
import {Grid,Row,Col,Image,Button} from 'react-bootstrap';
import { Link } from "react-router";

export default class Mem extends React.Component{

    render(){
        return (
            <Row className="mem">
            <Col sm={0} md={1} />
            <Col sm={12} md={10} >
            <h2>{this.props.title}</h2>
            <hr />
                <Link className={"mem-"+this.props.id} key={'mem-'+this.props.id} to={"/showMem/"+this.props.id}>
                <Image className="img-responsive center-block" src={"http://localhost:8081/mem/getFile/"+this.props.id+"/"+this.props.fileType}/>
                </Link>
                <div className="buttonTollbar">
                    <Button bsStyle="success"><span className="glyphicon glyphicon-thumbs-up"></span></Button>
                    <span className="number">55</span>
                    <Button bsStyle="danger"><span className="glyphicon glyphicon-thumbs-down"></span></Button>
                    <span className="number">12</span>
                        <span className="glyphicon glyphicon-comment pull-right"><span className="numberComments">5</span></span>

                </div>
            </Col>
            <Col sm={0} md={1} />
            </Row>
        );
    }
};
