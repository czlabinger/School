#ifndef CUSTOM_PDO_NAME_H
#define CUSTOM_PDO_NAME_H

//-------------------------------------------------------------------//
//                                                                   //
//     This file has been created by the Easy Configurator tool      //
//                                                                   //
//     Easy Configurator project setup.prj
//                                                                   //
//-------------------------------------------------------------------//


#define CUST_BYTE_NUM_OUT	2
#define CUST_BYTE_NUM_IN	4
#define TOT_BYTE_NUM_ROUND_OUT	4
#define TOT_BYTE_NUM_ROUND_IN	4


typedef union												//---- output buffer ----
{
	uint8_t  Byte [TOT_BYTE_NUM_ROUND_OUT];
	struct
	{
		uint8_t     update_received;
		uint8_t     done;
	}Cust;
} PROCBUFFER_OUT;


typedef union												//---- input buffer ----
{
	uint8_t  Byte [TOT_BYTE_NUM_ROUND_IN];
	struct
	{
		uint8_t     cube;
		uint8_t     position;
		uint8_t     update;
		uint8_t     done_received;
	}Cust;
} PROCBUFFER_IN;

#endif